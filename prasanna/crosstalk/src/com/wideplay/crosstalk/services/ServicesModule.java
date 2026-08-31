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


package com.wideplay.crosstalk.services;

import com.google.sitebricks.http.Post;
import com.google.sitebricks.http.Get;
import com.google.inject.AbstractModule;
import static com.google.inject.matcher.Matchers.*;
import com.wideplay.crosstalk.web.LoginPage;
import com.wideplay.crosstalk.tweets.Tweet;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AnnotationConfiguration;

public final class ServicesModule extends AbstractModule {
    private static final String HIBERNATE_CONFIG = "hibernate.properties";

    @Override
    protected void configure() {

        //configure persistence services, using hibernate
        install(PersistenceService
                .usingHibernate()
                .across(UnitOfWork.REQUEST)
                .buildModule()
        );


        //configure hibernate with our tweet data model class
        bind(Configuration.class).toInstance(new AnnotationConfiguration()
                .addAnnotatedClass(Tweet.class)
                .setProperties(loadProperties(HIBERNATE_CONFIG))
        );


        //configure crosstalk data services
        bind(TweetManager.class).to(HibernateTweetManager.class);
        bind(UserManager.class).to(InMemoryUserManager.class);


        //configure the in-memory authenticator (with hard-coded users)
        final HttpSessionAuthenticationManager manager = new HttpSessionAuthenticationManager();
        bind(AuthenticationManager.class).toInstance(manager);


        //intercept any @Get or @Post method on any page except the LoginPage, for security
        bindInterceptor(
                not(subclassesOf(LoginPage.class)),
                annotatedWith(Get.class).or(annotatedWith(Post.class)),

                new SecurityInterceptor(manager)
        );
    }

    private static Properties loadProperties(String props) {
        Properties result = new Properties();
      return result;
    }
}
