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

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static org.easymock.EasyMock.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class HomePageIntegrationTest {
	private Injector injector;

	@BeforeTest
	public final void prepareContainer() {
		injector = Guice.createInjector(new WebModule(),
 					new TestPersistenceModule());
	}

	@AfterTest
	public final void cleanup() {  }

	@Test
	public final void renderHomePage() throws Exception {
		HttpServletRequest request = createMock(HttpServletRequest.class);
		HttpServletResponse response = createMock(HttpServletResponse.class);

		injector.getInstance(HomeServlet.class)
			.service(request, response);

		//assert something about response
	}
}

class WebModule extends AbstractModule {
  protected void configure() {

  }
}

class TestPersistenceModule extends AbstractModule {
  protected void configure() {

  }
}

class HomeServlet extends HttpServlet {}