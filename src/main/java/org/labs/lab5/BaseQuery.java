package org.labs.lab5;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseQuery<T> implements QueryCondition {
  private static final String UPDATE_QUERY = "UPDATE %s SET %s = %s WHERE %s;";
  private static final String SELECT_QUERY = "SELECT * FROM %s;";
  private static final String DELETE_QUERY = "DELETE FROM %s WHERE %s";
  private static final String INSERT_QUERY = "INSERT INTO %s (%s) VALUES(%s);";

  private final Table table;
  private final Field[] fields;

  BaseQuery(Class<T> type) {
    this.table = type.getDeclaredAnnotation(Table.class);
    this.fields = type.getDeclaredFields();
  }

  public String select() {
    return String.format(SELECT_QUERY, table.value());
  }

  public String delete(Object value) throws Exception {
    return String.format(DELETE_QUERY, table.value(), check(value));
  }

  public String update(Object value) throws Exception {
    return String.format(UPDATE_QUERY, table.value(), value.toString(), check(value));
  }

  public <T> String insert(T object) throws Exception {
    var record = getRecord(object);
    var columns = new StringBuilder();
    var values = new StringBuilder();
    for (var entry : record.entrySet()) {
      columns.append(entry.getKey() + ",");
      values.append(entry.getValue() + ",");
    }
    columns.deleteCharAt(columns.length() - 1);
    values.deleteCharAt(values.length() - 1);
    return String.format(INSERT_QUERY, table.value(), columns.toString(), values.toString());
  }

  protected <R> String check(Class<R> type, Object value) throws Exception {
    var methods = type.getMethods();
    for (var method : methods) {
      if (method != null) {
        var condition = method.getAnnotation(Condition.class);
        return String.format(condition.value(), value.toString());
      }
    }
    throw new Exception();
  }

  private <T> Map<String, String> getRecord(T object) throws Exception {
    var record = new HashMap<String, String>();
    for (var field : fields) {
      var annotation = field.getAnnotation(Column.class);
      var entry = annotation != null ? annotation.value() : field.getName();
      var value = field.get(object);
      if (value instanceof String s) {
        record.put(entry, "\'" + s + "\'");
      } else {
        record.put(entry, value.toString());
      }
    }
    return record;
  }
}
