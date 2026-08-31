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


package c5;

import com.google.inject.*;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class A {

  public static class BasementFloor {
    private final Camera camera1;
    private final Camera camera2;

    @Inject
    public BasementFloor(@Basement Camera camera1,
               @Basement Camera camera2) {

      this.camera1 = camera1;
       this.camera2 = camera2;
    }
  }

  public static class ControlRoom {
    private final MasterTerminal terminal1;
    private final MasterTerminal terminal2;

    private final MasterTerminal terminal3;
    private final MasterTerminal terminal4;


    @Inject
    public ControlRoom(MasterTerminal terminal1,
              MasterTerminal terminal2,
              @Basement MasterTerminal terminal3,
              @Basement MasterTerminal terminal4) {

      this.terminal1 = terminal1;
      this.terminal2 = terminal2;
      this.terminal3 = terminal3;
      this.terminal4 = terminal4;
    }
  }



  public class BuildingModule extends AbstractModule {

    @Override
    protected void configure() {
  bind(Camera.class).annotatedWith(Basement.class).to(SimpleCamera.class);
  bind(Camera.class).annotatedWith(Penthouse.class).to(SimpleCamera.class);

      bind(MasterTerminal.class).in(Singleton.class);

      bind(MasterTerminal.class).annotatedWith(Basement.class)
          .to(BasementTerminal.class).in(Singleton.class);

    }
  }

  @Singleton
  public static class MasterTerminal {  }

  public static class BasementTerminal extends MasterTerminal {  }

  interface Camera {}

  @BindingAnnotation
  @Retention(RetentionPolicy.RUNTIME)
  @interface Basement {}

  @BindingAnnotation
  @Retention(RetentionPolicy.RUNTIME)
  @interface Penthouse {}

  static class SimpleCamera implements Camera {}

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new A().new BuildingModule());
    BasementFloor bf = injector.getInstance(BasementFloor.class);
    Building b = injector.getInstance(Building.class);
    ControlRoom cr = injector.getInstance(ControlRoom.class);


    new A().main();
  }


public class StockTicker {
	private Console console = Console.getInstance();

	public void tick() {
		//print to console
	}
}

public class StockTickerTest {
	private Console previous;

	@BeforeMethod
	void setup() {
		previous = Console.getInstance();
	}

	@Test
	public final void printToConsole() {
		Console.setInstance(new MockConsole());
	}

	@AfterMethod
	void teardown() {
		Console.setInstance(previous);
	}
}


  public class StockTickerTest2 {

    @Test
    public final void printToConsole() {
      Console previous = Console.getInstance();
      try {
        Console.setInstance(new MockConsole());

      } finally {
        Console.setInstance(previous);
      }
    }
  }

  class MockConsole extends Console {}


public static class Console {
	private static Console instance = null;

	public static synchronized Console getInstance() {
		if (null == instance)
			instance = new Console();

		return instance;
	}

	public static synchronized void setInstance(Console console) {
		instance = console;
	}

}




  public static class Building {
    private final Camera camera1;
    private final Camera camera2;
    private final Camera camera3;
    private final Camera camera4;


    @Inject
    public Building(@Basement Camera camera1,
               @Basement Camera camera2,
               @Penthouse Camera camera3,
               @Penthouse Camera camera4) {

      this.camera1 = camera1;
       this.camera2 = camera2;
       this.camera3 = camera3;
       this.camera4 = camera4;
    }
  }


  public void main() {
    Family family = new Family();

    MutablePicoContainer injector = new DefaultPicoContainer();
    injector.addComponent(Toothpaste.class);

    family.give("Joanie", injector.getComponent(Toothpaste.class));
    family.give("Jackie", injector.getComponent (Toothpaste.class));
  }

  class Toothpaste {}
  class FluorideToothpaste extends Toothpaste {}
  class Family {
    void give(String name, Toothpaste t) {}
  }
}
