package org.labs.lab2;

public abstract class XmlSerializeTest<T> extends SerializeTest<T> {
  XmlSerializeTest(Class<T> clazz) {
    super(clazz);
  }

  @Override
  protected Serialize<T> getSerializeObject() {
    return new XmlSerialize<T>(getClazz());
  }

  @Override
  protected String getFilename() {
    return "test.xml";
  }
}
