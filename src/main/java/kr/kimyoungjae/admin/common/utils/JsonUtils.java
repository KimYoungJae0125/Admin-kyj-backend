package kr.kimyoungjae.admin.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils<T> {
    private static JsonMapper jsonMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    public static<T> String objectToJson(T data) {
        try {
            return jsonMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static<T> T jsonToObject(String jsonString, Class<T> clazz) {
        try {
            return jsonMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
