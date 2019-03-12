package com.magento.example.domain;

public class NamesOnly {
  private String firstName;
  private String lastName;

  public NamesOnly(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

}
