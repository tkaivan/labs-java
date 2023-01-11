package org.labs.lab5;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonService {
  PostgreSqlManager psql;
  static final String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

  public void init() throws Exception {
    psql = new PostgreSqlManager(url);

    var statement = psql.statement();
    statement.execute(
        "DROP TABLE persons; CREATE TABLE IF NOT EXISTS persons(id INTEGER, first_name VARCHAR(50), last_name VARCHAR(50));");

    var queris = new PersonQueries();

    psql.sync(queris);
  }

  public void addToDb(Person person) throws Exception {
    psql.insert(person);
  }

  public ResultSet getAll() throws Exception {
    return psql.selectAll();
  }

  public ResultSet delete(String query) throws Exception {
    return psql.delete(query);
  }

  public ResultSet update(String column_name, Object value, String condition) throws Exception {
    return psql.update(column_name, value, condition);
  }

  public void close() throws SQLException {
    psql.close();
  }

  public ResultSet selectAll() throws Exception {
    return psql.selectAll();
  }

  public ResultSet select(int id) throws Exception {
    return psql.select(id);
  }
}
