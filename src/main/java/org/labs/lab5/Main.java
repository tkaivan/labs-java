package org.labs.lab5;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    try {
      PersonService personService = new PersonService();
      personService.init();

      Person person = Person.builder().setId(1).setFirstName("Ivan").setLastName("Tkachuk").build();

      System.out.println("Inserts persons");

      personService.addToDb(person);

      personService.addToDb(Person.builder().setId(2).setFirstName("Andrii").setLastName("Liashenko").build());

      printResultSet(personService.selectAll());

      System.out.println("Delete person with id = 1");

      var deletedPerson = personService.delete("id = 1");
      printResultSet(deletedPerson);

      System.out.println("Update person name with id = 2");

      personService.update("first_name", "FirstName", "id = 2");
      personService.update("last_name", "LastName", "id = 2");

      var updatedPerson = personService.select(2);
      System.out.println(updatedPerson);

      personService.close();

    } catch (Exception e) {
      System.err.println(e);
    }
  }

  static void printResultSet(ResultSet resultSet) throws SQLException {
    var result = resultSet.getMetaData();
    int columnsNumber = result.getColumnCount();
    while (resultSet.next()) {
      for (int i = 1; i <= columnsNumber; i++) {
        if (i > 1)
          System.out.print(",  ");
        String columnValue = resultSet.getString(i);
        System.out.print(columnValue + " " + result.getColumnName(i));
      }
      System.out.println("");
    }

  }
}
