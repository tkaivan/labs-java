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

  public <T> void insert(T object) throws Exception {
    if (canExecute()) {
      statement.addBatch(queries.insert(object));
      statement.executeBatch();
      return;
    }
    throw new Exception("Cannot execute");
  }

  public void update(Object value) throws Exception {
    if (canExecute()) {
      statement.addBatch(queries.update(value));
      statement.executeBatch();
      return;
    }
    throw new Exception("Cannot execute");
  }

  public void delete(Object value) throws Exception {
    if (canExecute()) {
      statement.addBatch(queries.delete(value));
      statement.executeBatch();
      return;
    }
    throw new Exception("Cannot execute");
  }

  public ResultSet select() throws Exception {
    if (canExecute()) {
      return statement.executeQuery(queries.select());
    }
    throw new Exception("Cannot execute");
  }

  private boolean canExecute() {
    return queries != null;
  }
}
