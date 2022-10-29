package org.labs.lab2;

public class TxtSerialize<T extends TxtSerializable<T>> implements Serialize<T> {
    Class<T> type;

    TxtSerialize(Class<T> type) {
        this.type = type;
    }

    @Override
    public String serialize(T object) {
        return object.serializeToString(object);
    }

    @Override
    public T deserialize(String data) throws Exception {
        return type.getConstructor().newInstance().deserializeFromString(data);
    }
}
