import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import c1.Foo;

public class Emailer {
	private SpellChecker spellChecker;

 	public Emailer(SpellChecker spellChecker) {
		this.spellChecker = spellChecker;
	}

	public void send(String text) {
		spellChecker.check(text);		
		// send if ok...
	}

  public static void main(String[] args) {
    BeanFactory injector = new FileSystemXmlApplicationContext("email.xml");
    Emailer emailer = (Emailer) injector.getBean("emailer.French");
    emailer.send("Hello!");

  }
}

class FrenchSpellChecker implements SpellChecker {			
	public boolean check(String text) {
		//perform some checking...
    System.out.println("checkingez " + text);
    return true;
	}
}

class LatinCharset {}

class EnglishSpellChecker implements SpellChecker {
  public EnglishSpellChecker(LatinCharset latinChars) {

  }

	public boolean check(String text) {
    System.out.println("checkin " + text);
    return false;
		//perform some checking...
	}
}

class JapaneseSpellChecker implements SpellChecker {
	public boolean check(String text) {
    System.out.println("chekcon" + text);
    return false;
		//perform some checking...
	}
}

