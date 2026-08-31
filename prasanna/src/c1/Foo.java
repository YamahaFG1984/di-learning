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


package c1;

import org.testng.annotations.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Foo {


  static class SpellChecker {}
  static class EnglishSpellChecker extends SpellChecker {}
  static class FrenchSpellChecker extends SpellChecker {}
  static class JapaneseSpellChecker extends SpellChecker {}


public class MockSpellChecker extends SpellChecker {
	private boolean didCheckSpelling = false;
	public boolean checkSpelling(String text) {
            didCheckSpelling = true;
    return true;
       }

       public boolean verifyDidCheckSpelling() { return didCheckSpelling; }
}

  


public static class Emailer {
	private SpellChecker spellChecker;
	public void send(String text) { }

  public void setSpellChecker(Object japaneseSpellChecker) {

  }

  public void setAddressBook(EmailBook emailBook) {
  }

  public void setTextEditor(Object simpleJapaneseTextEditor) {

  }
}

  static class EmailBook {}
  static class PhoneAndEmailBook {}

  static class SimpleJapaneseTextEditor {}
  static class SimpleFrenchTextEditor {}

  void main() {
    Emailer emailer = (Emailer) new ServiceLocator().get("Emailer");
    
    Injector injector = Guice.createInjector();
    emailer = injector.getInstance(Emailer.class);
    emailer.send("Hello!");
  }


  @Test
  public void ensureEmailerChecksSpelling() {
Emailer emailer = new EmailerFactory().newJapaneseEmailer();

      MockSpellChecker mock = new MockSpellChecker();
      emailer.send("Hello there!");

      assert mock.verifyDidCheckSpelling();
  }

  class MockEmailer extends Emailer {
    public boolean correctlySent() {
      return false;
    }
  }
  public class EmailClient {
      private Emailer emailer = new EmailerFactory().newEmailer();

      public void run() {
          emailer.send(someMessage());

          confirm("Sent!");
      }
  }

  public static class EmailerFactory {
    private static Emailer instance;


    public Emailer newEmailer() {
        if (null == instance)
            return new Emailer();

        return instance;
    }

    static void set(Emailer mock) {
        instance = mock;
    }
    public Emailer newJapaneseEmailer() {
      Emailer service = new Emailer();
      service.setSpellChecker(new JapaneseSpellChecker());
      service.setAddressBook(new EmailBook());
//		  service.setAddressBook(new PhoneAndEmailBook());
      service.setTextEditor(new SimpleJapaneseTextEditor());
      return service;
    }
    public Emailer newFrenchEmailer() {
      Emailer service = new Emailer();
      service.setSpellChecker(new FrenchSpellChecker());
      service.setAddressBook(new EmailBook());
      service.setTextEditor(new SimpleFrenchTextEditor());
      return service;
    }
  }

  static class ServiceLocator {
    Object get(String name) {
      return null;
    }
  }


  public class SimpleEmailClient {
    private Emailer emailer;
    public SimpleEmailClient(Emailer emailer) {
      this.emailer = emailer;
    }
    public void sendEmail() {
      emailer.send(readMessage());                             
    }
  }

  String readMessage() {
    return null;
  }

  void foo() {
    Emailer emailer = (Emailer) new ServiceLocator()
      .get("JapaneseEmailerWithPhoneAndEmail");
  }

  @Test
    public void testEmailClient() {
      MockEmailer mock = new MockEmailer();
      EmailerFactory.set(mock);
      try {
        new EmailClient().run();

        assert mock.correctlySent();
      } finally {
        EmailerFactory.set(null);
      }
    }

  String someMessage() { return "foo"; }
  void confirm(String message) {  }

  @Test
  public void testEmailer() {
      MockSpellChecker spellChecker = new MockSpellChecker();
      //...

      Emailer emailer = new Emailer();
      emailer.setSpellChecker(spellChecker);
      //...

      emailer.send("Hello there!");

      //verify emailer's behavior
      assert false;
  }

}
