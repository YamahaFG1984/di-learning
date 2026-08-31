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

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Inject;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class B {


  public class BooksModule extends AbstractModule {

  @Override
	protected void configure() {
	}
}


  public class BookCatalogTest {
    private Injector injector;

    @BeforeMethod
    public final void setup() {
    }

    @Test
    public final void freeFormBookSearch() {
      new SimpleBookCatalog(injector.getInstance(Library.class))
         .search("..");

    }
  }

  public class LongLived {
    private final Dependency dep;

    @Inject
    public LongLived(Dependency dep) {
      this.dep = dep;
    }

    public void go() {
      int result = dep.calculate();
    }
  }
  

  interface DependencyA {
    int calculate();
  };
  interface DependencyB {
    int calculate();
  };
  interface Rebindable {}


public interface Dependency {
	int calculate();
}

  public class DependencyAdapter implements Dependency, Rebindable {
    private final DependencyA a;
    private final DependencyB b;
    private boolean useA = true;

    @Inject
    public DependencyAdapter(DependencyA a, DependencyB b) {
      this.a = a;
      this.b = b;
    }

    public void rebind() {
      useA = false;
    }

    public int calculate() {
      if (useA)
        return a.calculate();

      return b.calculate();
    }
  }


}

