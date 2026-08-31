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


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;


public class TransactionScope implements Scope {
	private final ThreadLocal<Map<String, Object>> instances
 			= new ThreadLocal<Map<String, Object>>();

	public Object get(String key, ObjectFactory unscoped) {
		Map<String, Object> map = instances.get();

		if (null == map)
			throw new IllegalStateException("no transaction is active");

		if (!map.containsKey(key)) {
			map.put(key, unscoped.getObject());
		}

		return map.get(key);

	}

	public void beginScope() {
		instances.set(new HashMap<String, Object>());
	}

	public void endScope() {
		instances.remove();
	}

	public Object remove(String key) {
		if (null == instances.get())
			throw new IllegalStateException("no transaction is active");

		return instances.get().remove(key);
	}

	public String getConversationId() {
		if (null == instances.get())
			throw new IllegalStateException("no transaction is active");

		return instances.get().toString();
	}

	public void registerDestructionCallback(String key,
 				Runnable destructionCallback) {
	}

  public static void main(String[] args) {
  }
}
