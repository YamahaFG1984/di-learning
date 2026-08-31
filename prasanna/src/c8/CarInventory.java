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


package c8;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.wideplay.warp.persist.Transactional;

public class CarInventory {
    private final EntityManager em;
    public CarInventory(EntityManager em) {
        this.em = em;
    }

  public void newCar(Car car) {
      EntityTransaction txn = em.getTransaction();
      txn.begin();
      boolean succeed = true;
      try {
          em.persist(car);
      } catch (RuntimeException e) {
          txn.rollback();
          succeed = false;
      } finally {
          if (succeed)
             txn.commit();
      }
  }
}
