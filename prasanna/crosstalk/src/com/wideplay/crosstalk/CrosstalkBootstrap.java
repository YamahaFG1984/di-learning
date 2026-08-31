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


package com.wideplay.crosstalk;

import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Guice;
import com.google.sitebricks.SitebricksModule;
import com.wideplay.crosstalk.services.ServicesModule;
import com.wideplay.crosstalk.web.HomePage;
import com.wideplay.crosstalk.web.LoginPage;
import com.wideplay.warp.persist.PersistenceFilter;

public final class CrosstalkBootstrap extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

      //bind in all of our service dependencies
      final Module services = new ServicesModule();



      //tell sitebricks to scan this package
      final Module sitebricks = new SitebricksModule() {
          @Override
          protected void configureSitebricks() {
            at("/home").show(HomePage.class);
            at("/login").show(LoginPage.class);
          }
      };

      //map all incoming requests through warp-persist's PersistenceFilter
      final Module servlets = new ServletModule() {
              protected void configureServlets() {
                  filter("/*").through(PersistenceFilter.class);

                   install(sitebricks);
              }
      };


        //finally, create the injector with all our configuration
        return Guice.createInjector(services, servlets);
    }
}
