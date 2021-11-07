package com.kms.seft203.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public abstract class ControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    public String convertObjectToJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public String convertObjectListToJsonString(List<Object> objectList) throws JsonProcessingException {
        String result = null;
        for (Object object : objectList) {
            result = objectMapper.writeValueAsString(object);
        }
        return result;
    }
}
