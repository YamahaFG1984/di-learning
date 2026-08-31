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


package com.wideplay.crosstalk.tweets;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Entity
public class Tweet {
    @Id @GeneratedValue
    private Long id;

    private String author;
    private String text;
    private Date createdOn;

    public Tweet() {
        this.createdOn = new Date();
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }


  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Tweet)) return false;

      Tweet that = (Tweet) o;

      return this.author.equals(that.author)
              && this.createdOn.equals(that.createdOn)
              && this.text.equals(that.text);
  }

  @Override
  public int hashCode() {
      int result;

      result = (author != null ? author.hashCode() : 0);
      result = 31 * result + (text != null ? text.hashCode() : 0);
      result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);

      return result;
  }
}
