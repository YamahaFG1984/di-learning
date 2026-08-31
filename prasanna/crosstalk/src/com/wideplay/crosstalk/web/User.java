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

import net.jcip.annotations.NotThreadSafe;
import com.google.inject.servlet.SessionScoped;

@SessionScoped
@NotThreadSafe
public class User {
    private String username;

    public String getUsername() {
        return username;
    }

    //logs in a user, by setting username to the current session
    public void login(String username) {
        this.username = username;
    }

    //logs out a user, by clearing username from session
    public void logout() {
        this.username = null;
    }
}
