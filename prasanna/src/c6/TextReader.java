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

import com.google.inject.*;

import java.util.List;
import java.util.Arrays;

@Singleton
public class TextReader {
	private final Provider<WordCounter> counterProvider;

  @Inject
	public TextReader(Provider<WordCounter> counterProvider) {
		this. counterProvider = counterProvider;
	}

  public void scan(List<String> strings) {
    for(String string : strings)
      counterProvider.get().count(string);

    System.out.println("Total words: " + counterProvider.get().words());
  }

  public static void main(String[] args) {
    Injector injector = Guice.createInjector();
    TextReader reader = injector.getInstance(TextReader.class);
    reader.scan(Arrays.asList("Dependency injection is good!", "Really, it is!"));

    TextReader reader2 = injector.getInstance(TextReader.class);
    reader2.scan(Arrays.asList("Dependency injection is terrific!", "Use it more!"));

    TextReader reader3 = injector.getInstance(TextReader.class);
    reader3.scan(Arrays.asList("The quick brown fox", "is really annoying!"));
  }
}
