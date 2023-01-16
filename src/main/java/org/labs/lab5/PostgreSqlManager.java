package org.labs.lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSqlManager {
  private final Connection connection;
  private Statement statement;
  private BaseQuery<?> queries;

  PostgreSqlManager(String url) throws Exception {
    Class.forName("org.postgresql.Driver");
    this.connection = DriverManager.getConnection(url);
    this.statement = connection.createStatement();
  }

  public <T extends BaseQuery<?>> void sync(T queries) {
    this.queries = queries;
  }

  public void close() throws SQLException {
    this.connection.close();
  }

  public <T> int insert(T object) throws Exception {
    if (canExecute()) {
      return statement.executeUpdate(queries.insert(object));
    }
    throw new Exception("Cannot execute");
  }

  public int update(String column_name, Object value, String condition) throws Exception {
    if (canExecute()) {
      return statement.executeUpdate(queries.update(column_name, value, condition));
    }
    throw new Exception("Cannot execute");
  }

  public int delete(String condition) throws Exception {
    if (canExecute()) {
      return statement.executeUpdate(queries.delete(condition));
    }
    throw new Exception("Cannot execute");
  }

  public ResultSet selectAll() throws Exception {
    if (canExecute()) {
      return statement.executeQuery(queries.selectAll());
    }
    throw new Exception("Cannot execute");
  }

  public ResultSet select(int id) throws Exception {
    if (canExecute()) {
      return statement.executeQuery(queries.selectById(id));
    }
    throw new Exception("Cannot execute");
  }

  public Statement statement() {
    return statement;
  }

  private boolean canExecute() {
    return queries != null;
  }
}
