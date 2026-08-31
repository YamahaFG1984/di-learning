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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Collection;

public class StringSearch {
	private final Collection<String> collection;

	public StringSearch(Collection<String> collection) {
		this.collection = collection;
	}

 	public String startsWith(String aString) {
		Iterator<String> iter = collection.iterator();
		while(iter.hasNext()) {
			String current = iter.next();

			if (current.startsWith(aString))
				return current;
		}

		return null;
	}

	public boolean contains(String aString) {
		Iterator<String> iter = collection.iterator();
		while(iter.hasNext()) {
			String current = iter.next();

			if (aString.equals(current))
				return true;
		}

		return false;
	}

}
