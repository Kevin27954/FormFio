package com.formkio.formfio.controller;

import com.formkio.formfio.model.WebFormSubmissionModel;
import com.formkio.formfio.services.FormSubmissionFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FormSubmission {

    protected final FormSubmissionFactory formSubmissionFactory;

    public FormSubmission(FormSubmissionFactory formSubmissionFactory) {
        this.formSubmissionFactory = formSubmissionFactory;
    }

    @PostMapping(value = "/submission", consumes = "application/x-www-form-urlencoded")
    public String acceptSubmission(@RequestParam Map<String, String> formData) {
        WebFormSubmissionModel webFormSubmissionModel = (WebFormSubmissionModel) formSubmissionFactory.create(formData, "web");
        // This should also be remade into a service function instead. that way
        // we can do a little more stuff. Mix the parser and the validator into
        // a single function?
        // Form form = formService.parseResponse(); ?
        Map<String, String> fields = webFormSubmissionModel.extractFields();

        // Parse the fields so that it can be decided whether or not it is a
        // valid submission.
        // This would be in the service, which we shall call: FormService

        System.out.println(fields.toString());

        webFormSubmissionModel.test();

        // After all of that we can save it to the database.
        // userFormRepo.save();

        return "success";
    }

}
