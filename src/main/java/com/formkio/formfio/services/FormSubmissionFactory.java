package com.formkio.formfio.services;

import com.formkio.formfio.model.FormSubmissionModel;
import com.formkio.formfio.model.WebFormSubmissionModel;
import com.formkio.formfio.repository.SubmissionTable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FormSubmissionFactory {

    private final SubmissionTable userFormRepo;

    public FormSubmissionFactory(SubmissionTable userFormRepo) {
        this.userFormRepo = userFormRepo;
    }

    public FormSubmissionModel create(Map<String, String> formData, String type) {
        FormSubmissionModel formSubmissionModel;

        switch (type) {
            case "web":
                formSubmissionModel = new WebFormSubmissionModel(userFormRepo, formData);
                break;
            default:
                formSubmissionModel = new FormSubmissionModel(userFormRepo, "null", new HashMap<String, String>());
                break;
        }

        return formSubmissionModel;
    }

    public FormSubmissionModel create(Map<String, String> formData) {
        return new FormSubmissionModel(userFormRepo, "empty", new HashMap<>());
    }
}
