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


package c5;

import com.google.inject.Singleton;
import com.google.inject.Provider;
import com.google.inject.Inject;

import javax.servlet.*;
import java.io.IOException;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.beans.factory.BeanFactory;

@Singleton
public class UserFilter implements Filter {
	private final Provider<UserContext> currentUser;

	@Inject
	public UserFilter(Provider<UserContext> currentUser) {
		this.currentUser = currentUser;
 	}

  public void init(FilterConfig filterConfig) throws ServletException {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void doFilter(ServletRequest request, ServletResponse response,
 		FilterChain chain) throws IOException, ServletException {

		currentUser.get().signIn("");

		chain.doFilter(request, response);
 	} 

  public void destroy() {
    
  }

  public static void main(String[] args) {
    BeanFactory injector = new FileSystemXmlApplicationContext("WEB-INF/comic-store.xml");
  }
}

