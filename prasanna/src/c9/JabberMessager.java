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

import com.google.inject.Inject;


class JabberMessager implements Messager {
    private final JabberTransport transport;
    private final JabberMessageConverter converter;

  @Inject
  JabberMessager(JabberTransport transport, JabberMessageConverter converter) {
    this.transport = transport;
    this.converter = converter;
  }

  public void send(Message msg) {
    }
}


class JabberTransport {
}

class JabberMessageConverter {
}
