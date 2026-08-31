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

import com.google.inject.Singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.Immutable;

@Immutable
@Singleton
class InMemoryUserManager implements UserManager {
    private final Map<String, String> users;

    public InMemoryUserManager() {
        final Map<String, String> users = new HashMap<String, String>();
        users.put("dhanji", "dirocks");

        //freeze the current hardcoded map so that it is thread-safe
        this.users = Collections.unmodifiableMap(users);
    }

    //returns true if a username and password combination are valid against
    //registered entires
    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
