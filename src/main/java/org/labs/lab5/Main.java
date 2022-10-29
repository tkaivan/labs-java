package org.labs.lab5;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    try {
      String url = "jdbc:postgresql://db:5432/test_db?user=seshotake";
      PostgreSqlManager psql = new PostgreSqlManager(url);

      Person person = Person.builder()
          .setId(1)
          .setFirstName("Ivan")
          .setLastName("Tkachuk")
          .build();

      PersonQueries queries = new PersonQueries();

      psql.sync(queries);

      psql.insert(person);

      ResultSet rs = psql.select();
      printResultSet(rs);

      psql.delete(1);

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
