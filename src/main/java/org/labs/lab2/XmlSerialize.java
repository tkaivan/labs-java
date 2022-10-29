package org.labs.lab2;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlSerialize<T> implements Serialize<T> {
    Class<T> type;

    XmlSerialize(Class<T> type) {
        this.type = type;
    }

    @Override
    public String serialize(T object) throws Exception {
        return new XmlMapper().writeValueAsString(object);
    }

    @Override
    public T deserialize(String data) throws Exception {
        return new XmlMapper().readValue(data, type);
    }
}
