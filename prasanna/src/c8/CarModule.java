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

import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.jpa.JpaUnit;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;

public class CarModule extends AbstractModule {

    @Override
    protected void configure() {
        install(PersistenceService.usingJpa()
                                  .buildModule());

        bindConstant().annotatedWith(JpaUnit.class).to("carDB");
    }

  public static void main(String[] args) {
    Guice.createInjector(new CarModule(), PersistenceService.usingJpa()
                                                            .buildModule());

  }

}
