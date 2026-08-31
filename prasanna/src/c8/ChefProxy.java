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

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

import org.aopalliance.intercept.MethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;

public class ChefProxy implements Chef {
    private final MethodInterceptor interceptor;
    private final Chef chef;

    public ChefProxy(MethodInterceptor interceptor, Chef chef) {
        this.interceptor = interceptor;
        this.chef = chef;
    }

    public void cook() {
    }

    public void clean() {
    }

  public static void main(String[] args) {
    InvocationHandler invocationHandler = null;
    Chef proxy = (Chef) Proxy.newProxyInstance(Chef.class.getClassLoader(),
                                              new Class[] { Chef.class },
        invocationHandler);

  }
}
