package com.formkio.formfio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formkio.formfio.services.EmailService;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJackson {

    @Value("${spring.resend.apikey}")
    String APIKEY;

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

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date today = Calendar.getInstance().getTime();
        Date next = cal.getTime();

        long diff = next.getTime() - today.getTime();
        int intDiff = (int) Math.ceil(diff / (float) (1000));

        System.out.println(intDiff);

        EmailService emailService = new EmailService();
        emailService.sendEmail();

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
