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

import java.util.Date;
import java.util.List;

public class JSR {
  public static class Person {
      private String name;

      private Date bornOn;

      private String email;

      private Address home;


      public void setName(String name) {
          this.name = name;
      }

      public Date getBornOn() {
          return bornOn;
      }

      public void setBornOn(Date bornOn) {
          this.bornOn = bornOn;
      }

      public String getEmail() {
          return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }

      public Address getHome() {
          return home;
      }

      public void setHome(Address home) {
          this.home = home;
      }
  }

  public static class Address {
      private String line1;

      private String line2;

      private String zipCode;

      private String city;

      private String country;

      public String getLine1() {
          return line1;
      }

      public void setLine1(String line1) {
          this.line1 = line1;
      }

      public String getLine2() {
          return line2;
      }

      public void setLine2(String line2) {
          this.line2 = line2;
      }

      public String getZipCode() {
          return zipCode;
      }

      public void setZipCode(String zipCode) {
          this.zipCode = zipCode;
      }

      public String getCity() {
          return city;
      }

      public void setCity(String city) {
          this.city = city;
      }

      public String getCountry() {
          return country;
      }

      public void setCountry(String country) {
          this.country = country;
      }
  }

  public static void main(String[] args) {


    Person lincoln = new Person();
    lincoln.setName("Abraham Lincoln");
    lincoln.setBornOn(new Date());
    lincoln.setEmail("abe@lincoln.nowhere");

    Address address = new Address();
    address.setLine1("1600 Pennsylvania Ave");
    address.setZipCode("51245");
    address.setCity("Washington, D.C.");
    address.setCountry("USA");

    lincoln.setHome(address);

    Validator validator = new Validator();

    List<InvalidValue> errors = validator.validate(lincoln);
    System.out.println(String.format("There were %d error(s)", errors.size()));

  }

  class InvalidValue {}

  static class Validator {
    List<InvalidValue> validate(Object o) { return null; }
  }


}
