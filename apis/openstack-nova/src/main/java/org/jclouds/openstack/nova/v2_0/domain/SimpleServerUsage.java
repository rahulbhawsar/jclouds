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
package org.jclouds.openstack.nova.v2_0.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;

import org.jclouds.javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.gson.annotations.SerializedName;

/**
 * Information the SimpleTenantUsage extension return data about each Server
 *
 * @author Adam Lowe
 */
public class SimpleServerUsage {
   
   public static enum Status {

      UNRECOGNIZED, ACTIVE;

      public String value() {
         return name();
      }

      public static Status fromValue(String v) {
         try {
            return valueOf(v.toUpperCase());
         } catch (IllegalArgumentException e) {
            return UNRECOGNIZED;
         }
      }

   }
   
   public static Builder<?> builder() {
      return new ConcreteBuilder();
   }

   public Builder<?> toBuilder() {
      return new ConcreteBuilder().fromSimpleServerUsage(this);
   }

   public static abstract class Builder<T extends Builder<T>>  {
      private String instanceName;
      private double hours;
      private double flavorMemoryMb;
      private double flavorLocalGb;
      private double flavorVcpus;
      private String tenantId;
      private String flavorName;
      private Date instanceCreated;
      private Date instanceTerminiated;
      private Status instanceStatus;
      private long uptime;

      protected abstract T self();

      public T instanceName(String instanceName) {
         this.instanceName = instanceName;
         return self();
      }

      public T hours(double hours) {
         this.hours = hours;
         return self();
      }

      public T flavorMemoryMb(double flavorMemoryMb) {
         this.flavorMemoryMb = flavorMemoryMb;
         return self();
      }

      public T flavorLocalGb(double flavorLocalGb) {
         this.flavorLocalGb = flavorLocalGb;
         return self();
      }

      public T flavorVcpus(double flavorVcpus) {
         this.flavorVcpus = flavorVcpus;
         return self();
      }

      public T tenantId(String tenantId) {
         this.tenantId = tenantId;
         return self();
      }

      public T flavorName(String flavorName) {
         this.flavorName = flavorName;
         return self();
      }

      public T instanceCreated(Date instanceCreated) {
         this.instanceCreated = instanceCreated;
         return self();
      }

      public T instanceTerminiated(Date instanceTerminiated) {
         this.instanceTerminiated = instanceTerminiated;
         return self();
      }

      public T instanceStatus(Status instanceStatus) {
         this.instanceStatus = instanceStatus;
         return self();
      }

      public T uptime(long uptime) {
         this.uptime = uptime;
         return self();
      }

      public SimpleServerUsage build() {
         return new SimpleServerUsage(this);
      }


      public T fromSimpleServerUsage(SimpleServerUsage in) {
         return this
               .instanceName(in.getInstanceName())
               .flavorMemoryMb(in.getFlavorMemoryMb())
               .flavorLocalGb(in.getFlavorLocalGb())
               .flavorVcpus(in.getFlavorVcpus())
               .tenantId(in.getTenantId())
               .flavorName(in.getFlavorName())
               .instanceCreated(in.getInstanceCreated())
               .instanceTerminiated(in.getInstanceTerminiated())
               .instanceStatus(in.getInstanceStatus())
               .uptime(in.getUptime())
               ;
      }

   }

   private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
      @Override
      protected ConcreteBuilder self() {
         return this;
      }
   }
   
   protected SimpleServerUsage() {
      // we want serializers like Gson to work w/o using sun.misc.Unsafe,
      // prohibited in GAE. This also implies fields are not final.
      // see http://code.google.com/p/jclouds/issues/detail?id=925
   }
  
   @SerializedName("name")
   private String instanceName;
   private double hours;
   @SerializedName("memory_mb")
   private double flavorMemoryMb;
   @SerializedName("local_gb")
   private double flavorLocalGb;
   @SerializedName("vcpus")
   private double flavorVcpus;
   @SerializedName("tenant_id")
   private String tenantId;
   @SerializedName("flavor")
   private String flavorName;
   @SerializedName("started_at")
   private Date instanceCreated;
   @SerializedName("ended_at")
   private Date instanceTerminiated;
   @SerializedName("state")
   private Status instanceStatus;
   private long uptime;

   private SimpleServerUsage(Builder<?> builder) {
      this.instanceName = checkNotNull(builder.instanceName, "instanceName");
      this.hours = builder.hours;
      this.flavorMemoryMb = builder.flavorMemoryMb;
      this.flavorLocalGb = builder.flavorLocalGb;
      this.flavorVcpus =  builder.flavorVcpus;
      this.tenantId = checkNotNull(builder.tenantId, "tenantId");
      this.flavorName = checkNotNull(builder.flavorName, "flavorName");
      this.instanceCreated = builder.instanceCreated; //checkNotNull(builder.instanceCreated, "instanceCreated");
      this.instanceTerminiated = builder.instanceTerminiated;
      this.instanceStatus = checkNotNull(builder.instanceStatus, "instanceStatus");
      this.uptime = checkNotNull(builder.uptime, "uptime");
   }

   /**
    */
   public String getInstanceName() {
      return this.instanceName;
   }

   /**
    */
   public double getFlavorMemoryMb() {
      return this.flavorMemoryMb;
   }

   /**
    */
   public double getFlavorLocalGb() {
      return this.flavorLocalGb;
   }

   /**
    */
   public double getFlavorVcpus() {
      return this.flavorVcpus;
   }

   /**
    */
   public String getTenantId() {
      return this.tenantId;
   }

   /**
    */
   public String getFlavorName() {
      return this.flavorName;
   }

   /**
    */
   public Date getInstanceCreated() {
      return this.instanceCreated;
   }

   /**
    */
   @Nullable
   public Date getInstanceTerminiated() {
      return this.instanceTerminiated;
   }

   /**
    */
   public Status getInstanceStatus() {
      return this.instanceStatus;
   }

   /**
    */
   public long getUptime() {
      return this.uptime;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(instanceName, flavorMemoryMb, flavorLocalGb, flavorVcpus, tenantId, flavorName, instanceCreated, instanceTerminiated, instanceStatus, uptime);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      SimpleServerUsage that = SimpleServerUsage.class.cast(obj);
      return Objects.equal(this.instanceName, that.instanceName)
            && Objects.equal(this.flavorMemoryMb, that.flavorMemoryMb)
            && Objects.equal(this.flavorLocalGb, that.flavorLocalGb)
            && Objects.equal(this.flavorVcpus, that.flavorVcpus)
            && Objects.equal(this.tenantId, that.tenantId)
            && Objects.equal(this.flavorName, that.flavorName)
            && Objects.equal(this.instanceCreated, that.instanceCreated)
            && Objects.equal(this.instanceTerminiated, that.instanceTerminiated)
            && Objects.equal(this.instanceStatus, that.instanceStatus)
            && Objects.equal(this.uptime, that.uptime)
            ;
   }

   protected ToStringHelper string() {
      return Objects.toStringHelper("")
            .add("instanceName", instanceName)
            .add("flavorMemoryMb", flavorMemoryMb)
            .add("flavorLocalGb", flavorLocalGb)
            .add("flavorVcpus", flavorVcpus)
            .add("tenantId", tenantId)
            .add("flavorName", flavorName)
            .add("instanceCreated", instanceCreated)
            .add("instanceTerminiated", instanceTerminiated)
            .add("instanceStatus", instanceStatus)
            .add("uptime", uptime)
            ;
   }

   @Override
   public String toString() {
      return string().toString();
   }

}
