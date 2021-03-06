package org.jclouds.io;

import static com.google.common.collect.Iterables.any;
import static javax.ws.rs.core.HttpHeaders.CONTENT_LENGTH;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.HttpHeaders.EXPIRES;

import java.util.Date;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.ws.rs.core.HttpHeaders;

import org.jclouds.crypto.CryptoStreams;
import org.jclouds.date.DateCodec;
import org.jclouds.date.DateCodecFactory;
import org.jclouds.io.ContentMetadataCodec.DefaultContentMetadataCodec;
import org.jclouds.logging.Logger;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import com.google.inject.ImplementedBy;
import com.google.inject.Inject;

@ImplementedBy(DefaultContentMetadataCodec.class)
public interface ContentMetadataCodec {

   /**
    * Generates standard HTTP headers for the give metadata.
    */
   public Multimap<String, String> toHeaders(ContentMetadata md);

   /**
    * Sets properties related to the http headers listed in {@link ContentMetadata#HTTP_HEADERS}
    */
   public void fromHeaders(MutableContentMetadata contentMetadata, Multimap<String, String> headers);

   /**
    * Parses the 'Expires' header.
    * If invalid, returns a date in the past (in accordance with HTTP 1.1 client spec).
    */
   public Date parseExpires(String expires);

   /**
    * Default implementation, in accordance with HTTP 1.1 spec.
    * 
    * @author aled
    */
   public static class DefaultContentMetadataCodec implements ContentMetadataCodec {
      
      @Resource
      protected Logger logger = Logger.NULL;

      private final DateCodec httpExpiresDateCodec;

      @Inject
      public DefaultContentMetadataCodec(DateCodecFactory dateCodecs) {
         httpExpiresDateCodec = dateCodecs.rfc1123();
      }
      
      protected DateCodec getExpiresDateCodec() {
         return httpExpiresDateCodec;
      }
      
      @Override
      public Multimap<String, String> toHeaders(ContentMetadata md) {
         Builder<String, String> builder = ImmutableMultimap.builder();
         if (md.getContentType() != null)
            builder.put(HttpHeaders.CONTENT_TYPE, md.getContentType());
         if (md.getContentDisposition() != null)
            builder.put("Content-Disposition", md.getContentDisposition());
         if (md.getContentEncoding() != null)
            builder.put(HttpHeaders.CONTENT_ENCODING, md.getContentEncoding());
         if (md.getContentLanguage() != null)
            builder.put(HttpHeaders.CONTENT_LANGUAGE, md.getContentLanguage());
         if (md.getContentLength() != null)
            builder.put(HttpHeaders.CONTENT_LENGTH, md.getContentLength() + "");
         if (md.getContentMD5() != null)
            builder.put("Content-MD5", CryptoStreams.base64(md.getContentMD5()));
         if (md.getExpires() != null)
            builder.put(HttpHeaders.EXPIRES, getExpiresDateCodec().toString(md.getExpires()));
         return builder.build();
      }
      
      @Override
      public void fromHeaders(MutableContentMetadata contentMetadata, Multimap<String, String> headers) {
         boolean chunked = any(headers.entries(), new Predicate<Entry<String, String>>() {
            @Override
            public boolean apply(Entry<String, String> input) {
               return "Transfer-Encoding".equalsIgnoreCase(input.getKey()) && "chunked".equalsIgnoreCase(input.getValue());
            }
         });
         for (Entry<String, String> header : headers.entries()) {
            if (!chunked && CONTENT_LENGTH.equalsIgnoreCase(header.getKey())) {
               contentMetadata.setContentLength(new Long(header.getValue()));
            } else if ("Content-MD5".equalsIgnoreCase(header.getKey())) {
               contentMetadata.setContentMD5(CryptoStreams.base64(header.getValue()));
            } else if (CONTENT_TYPE.equalsIgnoreCase(header.getKey())) {
               contentMetadata.setContentType(header.getValue());
            } else if ("Content-Disposition".equalsIgnoreCase(header.getKey())) {
               contentMetadata.setContentDisposition(header.getValue());
            } else if ("Content-Encoding".equalsIgnoreCase(header.getKey())) {
               contentMetadata.setContentEncoding(header.getValue());
            } else if ("Content-Language".equalsIgnoreCase(header.getKey())) {
               contentMetadata.setContentLanguage(header.getValue());
            } else if (EXPIRES.equalsIgnoreCase(header.getKey())) {
               contentMetadata.setExpires(parseExpires(header.getValue()));
            }
         }
      }
      
      public Date parseExpires(String expires) {
         try {
            return (expires != null) ? getExpiresDateCodec().toDate(expires) : null;
         } catch (IllegalArgumentException e) {
            logger.debug("Invalid Expires header (%s); should be in RFC-1123 format; treating as already expired: %s",
                  expires, e.getMessage());
            return new Date(0);
         }
      }
   }
}
