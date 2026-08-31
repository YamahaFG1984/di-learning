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


package c9;

public class UserManager {
    private final UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createNewUser(User user) {
        validate(user);
        userDao.save(user);
    }

    public void updatePassword(long userId, String password) {
        User user = userDao.read(userId);
        user.setPassword(password);

        validate(user);
        userDao.save(user);
    }

    public void deactivate(long userId) {
        User user = userDao.read(userId);
        user.deactivate();

        userDao.save(user);
    }

  void validate(User u) {}
}
