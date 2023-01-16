package org.labs.lab5;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    try {
      PersonService personService = new PersonService();

      Person person = Person.builder().setId(1).setFirstName("Ivan").setLastName("Tkachuk").build();

      int insertCount = personService.addToDb(person);
      System.out.println("Inserted " + insertCount + " persons");

      personService.addToDb(Person.builder().setId(2).setFirstName("Andrii").setLastName("Liashenko").build());

      printResultSet(personService.selectAll());

      int deleteCount = personService.delete("id = 1");
      System.out.println("Deleted " + deleteCount + " persons");

      System.out.println("Update person name with id = 2");

      personService.update("first_name", "Ivan", "id = 2");
      int updateCount = personService.update("last_name", "Tkachuk", "id = 2");
      System.out.println("Updated " + updateCount + " persons");

      printResultSet(personService.selectAll());

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
