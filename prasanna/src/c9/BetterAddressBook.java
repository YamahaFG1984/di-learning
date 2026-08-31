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

import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;

public class BetterAddressBook {
    private final List<String> names;

    public BetterAddressBook(List<String> names) {
        this.names = Collections.unmodifiableList(names);
    }

    public List<String> getNames() {
        return names;
    }

  public static void main(String[] args) {

    List<String> physicists = new ArrayList<String>();
    physicists.addAll(Arrays.asList("Landau", "Weinberg", "Hawking"));

    BetterAddressBook book = new BetterAddressBook(physicists);

    physicists.add("Einstein");

  }
}
