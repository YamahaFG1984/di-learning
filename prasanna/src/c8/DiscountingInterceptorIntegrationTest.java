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

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import static com.google.inject.matcher.Matchers.*;
import org.junit.Test;

public class DiscountingInterceptorIntegrationTest {
    @Test
    public void discountEveryHundredthTicket() {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(TicketBooth.class);
                bindInterceptor(any(), any(), new DiscountingInterceptor());
            }
        });

        TicketBooth booth = injector.getInstance(TicketBooth.class);

        for(int i = 1; i < 101; i++) {
            Ticket ticket = booth.purchase(Money.inDollars("200"));

            if (i == 100)
              System.out.println(ticket instanceof DiscountedTicket);
        }

    }
}
