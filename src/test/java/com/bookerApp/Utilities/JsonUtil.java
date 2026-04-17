package com.bookerApp.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Object could not be converted to JSON.", e);
        }
    }
    public static <T> T fromResource(String fileName, Class<T> x) {
        try (InputStream inputStream = JsonUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("JSON resource not found: " + fileName);
            }
            return OBJECT_MAPPER.readValue(inputStream, x);
        } catch (Exception e) {
            throw new RuntimeException("JSON resource could not be parsed: " + fileName, e);
        }
    }
}
