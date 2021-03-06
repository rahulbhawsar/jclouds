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
package org.jclouds.cloudstack.domain;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

/**
 * @author Richard Downer
 */
public class SnapshotPolicy implements Comparable<SnapshotPolicy> {

   public static Builder builder() {
      return new Builder();
   }

   public static class Builder {

      private String id;
      private Snapshot.Interval interval;
      private long numberToRetain;
      private String schedule;
      private String timezone;
      private String volumeId;

      /**
       * @param id the ID of the snapshot policy
       */
      public Builder id(String id) {
         this.id = id;
         return this;
      }

      /**
       * @param interval valid types are hourly, daily, weekly, monthy, template, and none.
       */
      public Builder interval(Snapshot.Interval interval) {
         this.interval = interval;
         return this;
      }

      /**
       * @param numberToRetain maximum number of snapshots retained
       */
      public Builder numberToRetain(long numberToRetain) {
         this.numberToRetain = numberToRetain;
         return this;
      }

      /**
       * @param schedule time the snapshot is scheduled to be taken.
       */
      public Builder schedule(String schedule) {
         this.schedule = schedule;
         return this;
      }

      /**
       * @param timezone the time zone of the snapshot policy
       */
      public Builder timezone(String timezone) {
         this.timezone = timezone;
         return this;
      }

      /**
       * @param volumeId ID of the disk volume
       */
      public Builder volumeId(String volumeId) {
         this.volumeId = volumeId;
         return this;
      }

      public SnapshotPolicy build() {
         return new SnapshotPolicy(id, interval, numberToRetain, schedule, timezone, volumeId);
      }
   }

   private String id;
   @SerializedName("intervaltype")
   private Snapshot.Interval interval;
   @SerializedName("maxsnaps")
   private long numberToRetain;
   private String schedule;
   private String timezone;
   @SerializedName("volumeid")
   private String volumeId;

   public SnapshotPolicy(String id, Snapshot.Interval interval, long numberToRetain, String schedule, String timezone, String volumeId) {
      this.id = id;
      this.interval = interval;
      this.numberToRetain = numberToRetain;
      this.schedule = schedule;
      this.timezone = timezone;
      this.volumeId = volumeId;
   }
   
   /**
    * present only for serializer
    */
   SnapshotPolicy() {
   }

   /**
    * @return the ID of the snapshot policy
    */
   public String getId() {
      return id;
   }

   /**
    * @return valid types are hourly, daily, weekly, monthy, template, and none.
    */
   public Snapshot.Interval getInterval() {
      return interval;
   }

   /**
    * @return maximum number of snapshots retained
    */
   public long getNumberToRetain() {
      return numberToRetain;
   }

   /**
    * @return time the snapshot is scheduled to be taken.
    */
   public String getSchedule() {
      return schedule;
   }

   /**
    * @return the time zone of the snapshot policy
    */
   public String getTimezone() {
      return timezone;
   }

   /**
    * @return ID of the disk volume
    */
   public String getVolumeId() {
      return volumeId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      SnapshotPolicy that = (SnapshotPolicy) o;

      if (!Objects.equal(id, that.id)) return false;
      if (!Objects.equal(numberToRetain, that.numberToRetain)) return false;
      if (!Objects.equal(volumeId, that.volumeId)) return false;
      if (!Objects.equal(interval, that.interval)) return false;
      if (!Objects.equal(schedule, that.schedule)) return false;
      if (!Objects.equal(timezone, that.timezone)) return false;

      return true;
   }

   @Override
   public int hashCode() {
       return Objects.hashCode(id, numberToRetain, volumeId, interval, schedule, timezone);
   }

   @Override
   public String toString() {
      return "SnapshotPolicy{" +
            "id=" + id +
            ", interval=" + interval +
            ", numberToRetain=" + numberToRetain +
            ", schedule='" + schedule + '\'' +
            ", timezone='" + timezone + '\'' +
            ", volumeId=" + volumeId +
            '}';
   }

   @Override
   public int compareTo(SnapshotPolicy other) {
      return id.compareTo(other.getId());
   }

}
