package com.formkio.formfio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formkio.formfio.exceptions.MalformDataError;
import org.springframework.stereotype.Component;

@Component
public class JSONParserService {

    public JSONParserService() {

    }

    /**
     * Given a JSON string representation, a parsed JsonObject of the string is returned.
     * @param data
     * @return JsonObject
     */
    public JsonNode parseJson(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(data);
        } catch (JsonProcessingException e) {
            System.out.println("JsonNode parseJson(): " + e);
            throw new MalformDataError("Malformed JSON data.");
        }
    }
}
