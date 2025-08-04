package com.formkio.formfio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formkio.formfio.exceptions.MalformDataError;
import org.springframework.stereotype.Component;

@Component
public class JSONService {

    public JSONService() {

    }

    /**
     * Given a JSON string representation, a parsed JsonObject of the string is returned.
     *
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


    public String jsonStringify(Object any) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(any);
        } catch (JsonProcessingException e) {
            System.out.println("String toJson(Object): " + e);
            throw new MalformDataError("Unable to convert to JSON");
        }

    }
}
