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

import com.google.inject.Provider;

import java.util.List;

public class NewsletterManager {
	private final List<Recipient> recipients;
	private final DelivererBuilder builder;					

	public NewsletterManager(List<Recipient> rs,
 				  DelivererBuilder db) {
		this.recipients = rs;
		this.builder = db;
	}

  public void send(Newsletter letter) {
    for (Recipient recipient : recipients) {
      builder.letter(letter);
      builder.mailServerUrl("mail.wideplay.com");
      builder.port(21);

      Deliverer d = builder.buildDeliverer();

      d.deliverTo(recipient);
    }
   }
}




class Newsletter {}
class Recipient {}