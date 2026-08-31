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

import com.wideplay.crosstalk.tweets.Tweet;
import com.wideplay.warp.persist.dao.Finder;
import com.google.inject.name.Named;
import com.google.inject.Provider;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import net.jcip.annotations.Immutable;

@Immutable
@Singleton
class HibernateTweetManager implements TweetManager {

    //the provider pattern helps us prevent scope-widening of sessions
    private final Provider<Session> session;

    @Inject
    public HibernateTweetManager(Provider<Session> session) {
        this.session = session;
    }


    @Finder(query = "from Tweet where author = :author")
    public List<Tweet> tweetsFor(@Named("author") String author) {

        //this method is intercepted by warp-persist DynamicFinders
        // and converted into a query. So you should not see an empty
        // list unless the database contains no Tweets for 'author'.
        return Collections.emptyList();
    }

    public void addTweet(Tweet tweet) {
        session.get().save(tweet);
    }
}
