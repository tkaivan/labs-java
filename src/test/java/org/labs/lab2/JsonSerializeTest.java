package org.labs.lab2;

public abstract class JsonSerializeTest<T> extends SerializeTest<T> {
  JsonSerializeTest(Class<T> clazz) {
    super(clazz);
  }

  @Override
  protected Serialize<T> getSerializeObject() {
    return new JsonSerialize<T>(getClazz());
  }

  @Override
  protected String getFilename() {
    return "test.json";
  }
}
