/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.openstack.quantum.v1_0.domain;

/**
 * A Quantum attachment
 *
 * @author Adam Lowe
 * @see <a href="http://docs.openstack.org/api/openstack-network/1.0/content/Attachments.html">api doc</a>
 */
public class Attachment extends Reference {

   public static Builder<?> builder() {
      return new ConcreteBuilder();
   }

   public Builder<?> toBuilder() {
      return new ConcreteBuilder().fromAttachment(this);
   }

   public static abstract class Builder<T extends Builder<T>> extends Reference.Builder<T> {
      protected abstract T self();

      public Attachment build() {
         return new Attachment(this);
      }

      public T fromAttachment(Attachment in) {
         return super.fromReference(in);
      }
   }

   private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
      @Override
      protected ConcreteBuilder self() {
         return this;
      }
   }

   protected Attachment(Builder<?> builder) {
      super(builder);
   }

   protected Attachment() {
      // for GSON
   }
}