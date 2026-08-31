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


package c4;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.text.Annotation;

public class A {

  public interface Customer {
    void waitInLine();

    boolean placeOrder(String choice);

    void pickup(Coffee prepared);
  }

  public interface Attendant {
    boolean takeOrder(String choice);

    Coffee prepareOrder();
  }

  interface Coffee {}

  public interface StringSearch {

    /**
     * Tests if a string is contained within its list.
      *
     * @param aString Any string to look for.
     * @returns Returns true only if a matching string is found.
     */
    boolean contains(String aString);

    /**
     * Tests if a string in the list begins with a given sequence.
     *
     * @param fragment A partial string to search on.
     * @returns Returns the first match found or null if
      * 	none were found.
     */
    String startsWith(String fragment);
  }
  

}

