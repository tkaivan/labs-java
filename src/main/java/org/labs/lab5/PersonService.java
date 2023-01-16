package org.labs.lab5;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonService {
  PostgreSqlManager psql;
  static final String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

  public PersonService() throws Exception {
    this.psql = new PostgreSqlManager(url);

    var statement = psql.statement();
    statement.execute(
        "DROP TABLE IF EXISTS persons; CREATE TABLE IF NOT EXISTS persons(id INTEGER, first_name VARCHAR(50), last_name VARCHAR(50));");
   
    var queries = new PersonQueries();

    psql.sync(queries);
  }

  public int addToDb(Person person) throws Exception {
    return psql.insert(person);
  }

  public ResultSet getAll() throws Exception {
    return psql.selectAll();
  }

  public int delete(String query) throws Exception {
    return psql.delete(query);
  }

  public int update(String column_name, Object value, String condition) throws Exception {
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
