package org.labs.lab2;

public abstract class TxtSerializeTest<T extends TxtSerializable<T>> extends SerializeTest<T> {
  TxtSerializeTest(Class<T> clazz) {
    super(clazz);
  }

  @Override
  protected Serialize<T> getSerializeObject() {
    return new TxtSerialize<T>(getClazz());
  }

  protected String getFilename() {
    return "test.txt";
  }
}
