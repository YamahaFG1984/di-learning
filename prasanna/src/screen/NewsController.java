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

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class NewsController implements Controller {
     private final PersistenceService persistence;
     private final NewsService newsService;

     public NewsController(PersistenceService persistence, NewsService newsService) {
         this.newsService = newsService; this.persistence = persistence;
     }

     public void init() {
         persistence.start();
     }

     public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) { return null; }
     public void destroy() {
         persistence.shutdown();
     }


  public static void main(String[] args) {
    BeanFactory injector = new FileSystemXmlApplicationContext("news.xml");
    injector.getBean("/news.html");
    Thumbnail t = (Thumbnail) injector.getBean("thumbnail");

  }

}
