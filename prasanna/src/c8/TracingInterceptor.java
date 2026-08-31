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

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TracingInterceptor implements MethodInterceptor, org.aopalliance.intercept.MethodInterceptor {
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                     throws Throwable {
        System.out.println("method " + method.getName() + " entered!");
        try {
            return methodProxy.invokeSuper(proxy, args);
        } finally {
            System.out.println("method " + method.getName() + " exited!");
        }
    }

  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    System.out.println("enter " + methodInvocation.getMethod().getName());
    Object result = methodInvocation.proceed();
    System.out.println("exit " + methodInvocation.getMethod().getName());
    return result;
  }

  public static void main(String[] args) {
    Chef originalChef = new Chef() {
      public void cook() {
        //To change body of implemented methods use File | Settings | File Templates.
      }

      public void clean() {
        //To change body of implemented methods use File | Settings | File Templates.
      }
    };

    Chef chef = (Chef) Enhancer.create(FrenchChef.class, new TracingInterceptor());
    chef.cook();
    chef.clean();

  }

}
