package org.labs.lab2;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerialize<T> implements Serialize<T> {
    Class<T> type;

    public JsonSerialize(Class<T> type) {
        this.type = type;
    }

    @Override
    public String serialize(T object) throws Exception {
        return new ObjectMapper().writeValueAsString(object);
    }

    @Override
    public T deserialize(String data) throws Exception {
        return new ObjectMapper().readValue(data, type);
    }
}
