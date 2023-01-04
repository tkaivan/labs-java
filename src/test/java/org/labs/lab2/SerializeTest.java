package org.labs.lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.labs.lab4.BadValidationException;

public abstract class SerializeTest<T> {
  Class<T> clazz;

  SerializeTest(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Test
  public void serializeToFile() throws Exception {
    var object = createObject();
    var filename = getFilename();
    getSerializeObject().serializeToFile(object, filename);
    var data = getSerializeObject().readFromFile(filename);
    var deserializedObject = getSerializeObject().deserialize(data);
    assertEquals(object, deserializedObject);
  }

  @Test
  public void deserializeFromFile() throws Exception {
    var object = createObject();
    var filename = getFilename();
    var data = getSerializeObject().serialize(object);
    getSerializeObject().writeToFile(data, filename);
    var deserializedObject = getSerializeObject().deserializeFromFile(filename);
    assertEquals(object, deserializedObject);
  }

  protected abstract Serialize<T> getSerializeObject();

  protected abstract T createObject() throws BadValidationException;

  protected abstract String getFilename();

  public Class<T> getClazz() {
    return clazz;
  }
}
