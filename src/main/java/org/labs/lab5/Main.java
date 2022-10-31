package org.labs.lab5;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    try {
      String url = "jdbc:postgresql://postgresdb:5432/postgres?user=postgres&password=postgres";
      PostgreSqlManager psql = new PostgreSqlManager(url);

      var statement = psql.statement();
      statement
          .execute("CREATE TABLE IF NOT EXISTS persons(id INTEGER, first_name VARCHAR(50), last_name VARCHAR(50));");

      Person person = Person.builder()
          .setId(1)
          .setFirstName("Ivan")
          .setLastName("Tkachuk")
          .build();

      PersonQueries queries = new PersonQueries();

      psql.sync(queries);

      System.out.println("Inserts persons");

      psql.insert(person);
      psql.insert(Person.builder()
          .setId(2)
          .setFirstName("Andrii")
          .setLastName("Liashenko")
          .build());

      ResultSet rs = psql.select();
      printResultSet(rs);

      System.out.println("Delete person with id = 1");

      psql.delete("id = 2");

      printResultSet(psql.select());

      System.out.println("Update person name with id = 1");

      psql.update("first_name", "FirstName", "id = 1");
      psql.update("last_name", "LastName", "id = 1");

      printResultSet(psql.select());

      psql.close();
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
