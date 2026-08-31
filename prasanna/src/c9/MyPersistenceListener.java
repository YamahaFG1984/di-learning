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

import com.opensymphony.oscache.base.persistence.PersistenceListener;
import com.opensymphony.oscache.base.persistence.CachePersistenceException;
import com.opensymphony.oscache.base.Config;

import java.util.Set;
import java.util.Properties;
import java.lang.reflect.InvocationTargetException;

public class MyPersistenceListener implements PersistenceListener {

  public boolean isStored(String s) throws CachePersistenceException {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public boolean isGroupStored(String s) throws CachePersistenceException {
    return false;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public void clear() throws CachePersistenceException {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public PersistenceListener configure(Config config) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public void remove(String s) throws CachePersistenceException {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void removeGroup(String s) throws CachePersistenceException {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public Object retrieve(String s) throws CachePersistenceException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public void store(String s, Object o) throws CachePersistenceException {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void storeGroup(String s, Set set) throws CachePersistenceException {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public Set retrieveGroup(String s) throws CachePersistenceException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public static void main(String[] args) {
    Properties config = new Properties();
    String className = config.getProperty("cache.persistence.class");

    Class<?> listener;
    try {
        listener = Class.forName(className);

    } catch (ClassNotFoundException e) {
        throw new RuntimeException("failed to find specified class", e);
    }

    Object instance;
    try {
        instance = listener.newInstance();
    } catch (InstantiationException e) {
        throw new RuntimeException("failed to instantiate listener from class", e);

    } catch (IllegalAccessException e) {
        throw new RuntimeException("failed to instantiate listener from class", e);
    }

  }
}
