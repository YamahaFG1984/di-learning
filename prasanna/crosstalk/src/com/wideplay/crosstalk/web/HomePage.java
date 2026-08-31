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


package com.wideplay.crosstalk.web;

import com.wideplay.crosstalk.tweets.Tweet;
import com.wideplay.crosstalk.services.TweetManager;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Get;
import com.google.sitebricks.http.Post;

import java.util.List;

@At("/home") /*@On("action")*/ @RequestScoped
public class HomePage {
    //user context, tracks current user
    private User user;

    //page state variables
    private List<Tweet> tweets;
    private Tweet newTweet = new Tweet();

    //service dependencies
    private final TweetManager tweetManager;

    @Inject
    public HomePage(TweetManager tweetManager, User user) {
        this.tweetManager = tweetManager;
        this.user = user;
    }


    @Get("logout")
    public String logout() {
        user.logout();

        return "/login?message=Bye.";
    }


    @Get
    public String get() {

        //load tweets for current user
        this.tweets = tweetManager.tweetsFor(user.getUsername());


        //stay on current page
        return null;
    }


    @Post
    public String post() {
        newTweet.setAuthor(user.getUsername());

        //contents are in newTweet, add it to the data store
        tweetManager.addTweet(newTweet);

        //redirect back to this page using a GET
        return "/home";
    }


    //getters/setters...

    public String getUser() {
        return user.getUsername();
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public Tweet getNewTweet() {
        return newTweet;
    }
}
