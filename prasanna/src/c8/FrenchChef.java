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
import com.google.inject.Inject;

import static com.google.inject.matcher.Matchers.*;

import java.math.BigInteger;

public class FrenchChef implements Chef {
    private int dishesCooked;
    private BigInteger potsWashed;
    private String currently;

    public void cook() {
        dishesCooked++;
        currently = "cooking";
    }

    public void clean() {
        potsWashed = potsWashed.add(BigInteger.ONE);
        currently = "cleaning";
    }

  public static void main(String...args) {
      FrenchChef chef = Guice.createInjector(new AbstractModule() {

          @Override
          protected void configure() {
              bind(FrenchChef.class);

            bindInterceptor(subclassesOf(Chef.class).or(subclassesOf(RecipeBook.class)), 
                                     any(), new TracingInterceptor());

          }

      }).getInstance(FrenchChef.class);

      chef.cook();
      chef.clean();
  }

}
