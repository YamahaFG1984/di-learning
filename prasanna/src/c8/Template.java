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

import static com.google.inject.matcher.Matchers.*;
import com.google.inject.Guice;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;

public class Template {
    private final String template = "Hello, :name!";

    public String process(String name) {
         return template.replaceAll(":name", name);
    }

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new AbstractModule() {
      protected void configure() {

        bindInterceptor(subclassesOf(Template.class), any(), new HtmlDecoratingInterceptor());
        bindInterceptor(subclassesOf(Template.class), any(), new BoldDecoratingInterceptor());
      }
    });

    Template template = injector.getInstance(Template.class);
    String message = injector
        .getInstance(Template.class)
        .process("Bob");


    System.out.println(message);
  }
}
