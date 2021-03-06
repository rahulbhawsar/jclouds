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
package org.jclouds.elb.features;

import java.util.concurrent.TimeUnit;

import org.jclouds.collect.PaginatedSet;
import org.jclouds.concurrent.Timeout;
import org.jclouds.elb.domain.LoadBalancer;
import org.jclouds.elb.options.ListLoadBalancersOptions;
import org.jclouds.javax.annotation.Nullable;

/**
 * Provides access to Amazon ELB via the Query API
 * <p/>
 * 
 * @see <a href="http://docs.amazonwebservices.com/ElasticLoadBalancing/latest/APIReference" />
 * @author Adrian Cole
 */
@Timeout(duration = 30, timeUnit = TimeUnit.SECONDS)
public interface LoadBalancerClient {
   
   /**
    * Retrieves information about the specified loadBalancer.
    * 
    * @param name
    *           Name of the loadBalancer to get information about.
    * @return null if not found
    */
   @Nullable
   LoadBalancer get(String name);

   /**
    * Returns detailed configuration information for the specified LoadBalancers. If there are none, the action returns an
    * empty list.
    * 
    * <br/>
    * You can paginate the results using the {@link ListLoadBalancersOptions parameter}
    * 
    * @param options
    *           the options describing the loadBalancers query
    * 
    * @return the response object
    */
   PaginatedSet<LoadBalancer> list(ListLoadBalancersOptions options);

   /**
    * Lists the loadBalancers all load balancers
    * 
    * @return the response object
    */
   PaginatedSet<LoadBalancer> list();

}
