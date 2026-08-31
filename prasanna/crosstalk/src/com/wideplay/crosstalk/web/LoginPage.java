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

import com.google.inject.servlet.RequestScoped;
import com.google.inject.Inject;
import com.google.sitebricks.At;
import com.google.sitebricks.http.Post;
import com.wideplay.crosstalk.services.UserManager;

@At("/login") @RequestScoped
public class LoginPage {
    private String username;
    private String password;

    private String message = "";

    //service dependencies
    private final UserManager userManager;
    private final User user;

    @Inject
    public LoginPage(UserManager userManager, User user) {
        this.userManager = userManager;
        this.user = user;
    }

    @Post
    public String login() {

        //attempt to authenticate the user
        if (userManager.authenticate(username, password))
            user.login(username);
        else {
            //clear user context from session
            user.logout();

            //stay on this page with error
            return "/login?message=Bad+credentials.";
        }

        //redirect to home page if successfully logged in
        return "/home";
    }


    //getters/setters...
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
