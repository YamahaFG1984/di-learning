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


package c6;

public class PowerStation {

  Transaction transaction;
  Chamber chamber1;
  Chamber chamber2;
  Chamber chamber3;

  public void start() {
      transaction.begin();

      boolean failed = false;
      try {
        chamber1.fire();
        chamber2.fire();
        chamber3.fire();

      } catch (StartFailedException e) {
        failed = true;
      } finally {
        if (failed)
          transaction.rollback();
        else
          transaction.commit();
       }
    }
}
