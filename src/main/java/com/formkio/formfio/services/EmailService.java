package com.formkio.formfio.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.resend.apikey}")
    private String APIKEY;

    public EmailService() {
    }

    public void sendEmail() {
        System.out.println(APIKEY);
//        Resend resend = new Resend(APIKEY);
//        CreateEmailOptions params = CreateEmailOptions.builder()
//                .from("onboarding@resend.dev")
//                .to("liukevin.nyc@gmail.com")
//                .subject("First test email")
//                .html("<strong>hello world</strong>")
//                .build();
//
//        try {
//            CreateEmailResponse res = resend.emails().send(params);
//            System.out.println(res.getId());
//        } catch (ResendException e) {
//            System.out.println("sendEmail(): " + e);
//            throw new RuntimeException("Unable to send email");
//        }
    }
}
