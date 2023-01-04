package org.labs.lab2;

import org.labs.lab1.Person;
import org.labs.lab4.BadValidationException;

public class JsonPersonTest extends JsonSerializeTest<Person> {
  public JsonPersonTest() {
    super(Person.class);
  }

  @Override
  protected Person createObject() throws BadValidationException {
    return Person.builder().setId(1).setFirstName("Ivan").setLastName("Tkachuk").build();
  }
}
