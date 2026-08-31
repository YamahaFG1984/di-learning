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

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Inject;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class Annotations {
  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Zip {
      String message() default "Zip invalid";
  }


  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Length {
      int min();
      int max();
  }


  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface NotNull {
      String message() default "Cannot be null";
  }

/**
* This class manages the creation of constraint validators.
*/
public interface ConstraintFactory {
    /**
    * Instantiate a Constraint.
    *
    * @return Returns a new Constraint instance
    * The ConstraintFactory is <b>not</b> responsible for calling Constraint#initialize
    */
    <T extends Constraint> T getInstance(Class<T> constraintClass);
}

  interface Constraint<T> {}

public class GuiceConstraintFactory implements ConstraintFactory {
    private final Injector injector = Guice.createInjector(new MyModule());

    public <T extends Constraint> T getInstance(Class<T> constraintKey) {
        return injector.getInstance(constraintKey);
    }

  class MyModule extends AbstractModule {

    protected void configure() {

      
    }
  }
}

/**
 * Check that a string length is between min and max
 *
 */
public class LengthConstraint implements Constraint<Length> {
    private int min;
    private int max;

    private final StringTools tools;

    @Inject
    public LengthConstraint(StringTools tools) {
        this.tools = tools;
    }

    /**
     * Configure the constraint validator based on the elements
     * specified at the time it was defined.
     *
     * @param constraint the constraint definition
     */
    public void initialize(Length constraint) {
        min = constraint.min();
        max = constraint.max();
    }

    /**
    * Validate a specified value.
    * returns false if the specified value does not conform to the definition
    * @exception IllegalArgumentException if the object is not of type String
    */
    public boolean isValid(Object value) {
        if (value == null) return true;

        if ( !(value instanceof String) )
            throw new IllegalArgumentException("Expected String type");

        String string = (String) value;
        return tools.isLengthBetween(min, max, string); 
    }
}

  public static void main(String[] args) {

  }



}
