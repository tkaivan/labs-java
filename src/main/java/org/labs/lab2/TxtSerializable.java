package org.labs.lab2;

/**
 * Implements Serialize for txt file
 *
 * @param <T> class for serialize
 */
public interface TxtSerializable<T> {

    /**
     * @param object object
     * @return serialized object to txt
     */
    String serializeToString(T object);

    /**
     * @param data txt data for deserialize
     * @return deserialized object
     */
    T deserializeFromString(String data);

    /**
     * @param string string to parse
     * @return parsed string
     */
    default String parse(String string) {
        return string.substring(string.lastIndexOf("=") + 1).replaceAll("'", "");
    }
}
