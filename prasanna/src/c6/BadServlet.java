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

import com.google.inject.Singleton;
import com.google.inject.Provider;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class BadServlet extends HttpServlet {
	public Provider<WebPage> page;
	public final List<WebPage> pages = new ArrayList<WebPage>();

	public void service(HttpServletRequest request, HttpServletResponse response) {
		pages.add(page.get());

		pages.get(0).handle(request, response);
 	}
}
