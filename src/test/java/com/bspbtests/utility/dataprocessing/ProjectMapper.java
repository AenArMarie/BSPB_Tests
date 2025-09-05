package com.bspbtests.utility.dataprocessing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProjectMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode mapJson(String jsonString) {
        try {
            return MAPPER.readTree(jsonString);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
