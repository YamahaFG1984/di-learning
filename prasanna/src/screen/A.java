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


package screen;

import com.google.inject.AbstractModule;
import com.google.inject.Stage;
import com.google.inject.Guice;

import javax.ejb.*;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;

public class A {
  @Remote
  public interface ShoppingCart {
      void add(Item item);
      List<Item> list();
  }

  @Stateful
  public class ShoppingCartEjb implements ShoppingCart {
      private List<Item> items = new ArrayList<Item>();
      private double discount;

      @EJB
      private InventoryEjb inventory;

      @PostConstruct
      public void prepareCart() {
          discount = inventory.todaysDiscount() * items.size();
      }

      @Remove
      public Status purchase() {
          return inventory.process(items, discount);
      }

@PrePassivate
public void passivate() {
    cache.store(items);
}

    @PostActivate
    public void activate() {
        items = inventory.refresh(items);
    }


    Cache cache;

      public void add(Item item) { items.add(item); }
      public List<Item> list() { return items; }
  }

  interface Cache {
    void store(Iterable<Item> items);
  }

  interface Item {}
  enum Status { FOO }
  interface InventoryEjb {
    short todaysDiscount();
    Status process(Iterable<Item> items, double discount);

    List<Item> refresh(List<Item> items);
  }

  public static void main(String[] args) {
    Guice.createInjector(Stage.PRODUCTION, new MyModule());
    Guice.createInjector(Stage.DEVELOPMENT, new MyModule());  
  }

  static class MyModule extends AbstractModule {
    protected void configure() {
      bind(MyServiceImpl.class).asEagerSingleton();
      bind(MyService.class).toInstance(new MyServiceImpl());
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  static class MyService {}
  static class MyServiceImpl extends MyService {}
}
