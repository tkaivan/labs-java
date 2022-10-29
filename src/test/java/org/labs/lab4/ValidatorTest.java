package org.labs.lab4;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.labs.lab1.City;
import org.labs.lab1.Person;

public class ValidatorTest {
  @Test
  public void zeroIdPerson() {
    assertThrows(IllegalArgumentException.class,
        () -> Person.builder().setId(0).setFirstName("Ivan").setLastName("Tkachuk").build());
  }

  @Test
  public void badFirstNamePerson() {
    assertThrows(IllegalArgumentException.class,
        () -> Person.builder().setId(1).setFirstName("1van").setLastName("Tkachuk").build());
  }

  @Test
  public void badLastNamePerson() {
    assertThrows(IllegalArgumentException.class,
        () -> Person.builder().setId(1).setFirstName("Ivan").setLastName("Tka4uk").build());
  }

  @Test
  public void badNameCity() {
    assertThrows(IllegalArgumentException.class,
        () -> City.builder().setName(null).setState("Ukraine").build());
  }
}
