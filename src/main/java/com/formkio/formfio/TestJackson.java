package com.formkio.formfio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class TestJackson {

    public static void main(String[] args) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMap = objectMapper.writeValueAsString(map);
        System.out.println(jsonMap);


        Test test = new Test();
        ObjectMapper objectMapper1 = new ObjectMapper();
        System.out.println(objectMapper1.writeValueAsString(test));
    }


    static class Test {
        public String field1;
        public String field2;
        public String field3;
        public String field4;

        public Test() {
            this.field1 = "field1";
            this.field2 = "field2";
            this.field3 = "field3";
            this.field4 = "field4";
        }

    }
}
