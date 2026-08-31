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

import java.util.logging.*;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import static com.google.inject.matcher.Matchers.*;

public class B {

  public static void main(String[] args) {
    final Painting picasso = new Painting("Picasso");

    Injector injector = Guice.createInjector(new AbstractModule() {
        protected void configure() {
            bind(Painting.class).toInstance(picasso);
            bindInterceptor(any(), any(), new TracingInterceptor());
        }
    });

    Painting j = injector.getInstance(Painting.class);

  }

  static class Painting {
    public Painting(String artist) {}
  }


public class ANoisyService {
    public final Logger log = Logger.getLogger(ANoisyService.class.getName());

}

public class EqualToNone {
    @Override
    public boolean equals(Object object) {
        if (object.getClass() != getClass())
            return false;

      return false;
    }
}

  
  

}
