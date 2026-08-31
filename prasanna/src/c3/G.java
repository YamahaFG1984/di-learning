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


package c3;

import com.google.inject.Inject;
import org.testng.annotations.Test;

public class G {

  public class Emailer {
    private SpellChecker spellChecker;

    public void setSpellChecker(SpellChecker spellChecker) {
      this. spellChecker = spellChecker;
    }
  }


  public class Amplifier {
    private Guitar guitar;
    private Speaker speaker;
    private Footpedal footpedal;
    private Synthesizer synthesizer;


    public void setGuitar(Guitar guitar) {
      this.guitar = guitar;
    }

    public void setSpeaker(Speaker speaker) {
      this.speaker = speaker;
    }

    public void setFootpedal(Footpedal footpedal) {
      this.footpedal = footpedal;
    }

    public void setSynthesizer(Synthesizer synthesizer) {		
      this.synthesizer = synthesizer;
    }

  }

public class BrewingVat {
	@Inject Barley barley;
	@Inject Yeast yeast;

	public Beer brew() {
		//make some beer from ingredients
    return null;
	}

}

public class Blender {
	private GlassJar jar;
	private Blades blades;

	@Inject
	public void setJar(GlassJar jar) {
		this.jar = jar;
 	}

	public void setBlades(Blades blades) {
		this.blades = blades;
	}

	public void frappe() {
		jar.fillWithOranges();
		blades.run();
	}
}

public class BlenderTest {

	@Test
	public void blendOranges() {
		new Blender().frappe();

		//assert something
 	}
}

public class Amphibian {
	private Gills gills;
	private Lungs lungs;
	private Heart heart;

	public Amphibian(Heart heart, Gills gills) {
		this.heart = heart;
		this.gills = gills;
	}

	public Amphibian(Heart heart, Lungs lungs) {
		this.heart = heart;
		this.lungs = lungs;
	}

}

public class Host {
	private final Symbiote symbiote;

	public Host(Symbiote symbiote) {
		this.symbiote = symbiote;
	}
}

public class Symbiote {
	private final Host host;

	public Symbiote(Host host) {
		this.host = host;
	}
}
  

   class Heart {}
   class Lungs {}
   class Gills {}

  
public class Spy {
	private String realName;
	private String britishAlias;
	private String americanAlias;

	public Spy(String name1, String name2, String name3) {  }
}

  class GlassJar {
    void fillWithOranges() {}
  }
  class Blades {
    void run() {}
  }


  class Earth {}

  public class Atlas {
    private final Earth earth;

    public Atlas(Earth earth) {
       this.earth = earth;
     }

    public void reset() {
    }
  }

  interface Beer {}
  interface Barley {}
  interface Yeast {}


  interface Synthesizer {}
  interface Speaker {}
  interface Footpedal {}
  interface Guitar {}
}
