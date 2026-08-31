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


package example;

import com.google.inject.Inject;

public class DelivererBuilder {
	private final MailServerFinder finder;

	private Newsletter letter;
	private String mailServerUrl;
	private int port;

	@Inject
	public DelivererBuilder(MailServerFinder finder) {
		this.finder = finder;
	}

 	public Deliverer buildDeliverer() {
		MailServer server = finder.findMailServer(mailServerUrl, port);

		return new Deliverer(letter);
	}

  interface MailServer {}
  interface MailServerFinder {
    MailServer findMailServer(String url, int port);
  }

	public void letter(Newsletter letter) {
		this.letter = letter;
	}

	public void mailServerUrl(String url) {
		this.mailServerUrl = url;
	}

	public void port(int port) {
		this.port = port;
	}

}
