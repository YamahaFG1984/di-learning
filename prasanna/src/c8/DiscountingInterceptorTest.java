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

import org.junit.Test;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.lang.reflect.AccessibleObject;

public class DiscountingInterceptorTest {
    @Test
    public void decorateEveryHundredthCall() throws Throwable {
        DiscountingInterceptor discounter = new DiscountingInterceptor();
        MockInvocation mi = new MockInvocation();

        for(int i = 1; i < 101; i++) {
            Object result = discounter.invoke(mi);

            if (i == 100)
              System.out.println(result instanceof DiscountedTicket);
        }
    }

  public static void main(String[] args) throws Throwable {
    new DiscountingInterceptorTest().decorateEveryHundredthCall();
  }

  class MockInvocation implements MethodInvocation {
    public Method getMethod() {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object[] getArguments() {
      return new Object[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object proceed() throws Throwable {
      return new TicketBooth().purchase(new Money());
    }

    public Object getThis() {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public AccessibleObject getStaticPart() {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
  }
}
