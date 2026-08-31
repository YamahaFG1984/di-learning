/**
 * Copyright (C) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package c8;

import org.aopalliance.intercept.MethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;

public class DiscountingInterceptor implements MethodInterceptor {
    private int count;

    public Object invoke(MethodInvocation mi) throws Throwable {
        Ticket ticket = (Ticket)mi.proceed();

        if (Ticket.INSUFFICIENT_FUNDS != ticket && (++count % 100) == 0) {

            return new DiscountedTicket(ticket);          
        }

        return ticket;
    }
}
