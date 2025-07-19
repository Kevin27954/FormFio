package com.formkio.formfio.model;

import com.formkio.formfio.repository.SubmissionTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebFormSubmissionModel extends FormSubmissionModel {

    public WebFormSubmissionModel(SubmissionTable userFormRepo, Map<String, String> formData) {
        super(userFormRepo, "web", formData);
    }

    @Override
    public Map<String, String> extractFields() {
        List<String> fields = this.userFormRepo.getFields("");
        Map<String, String> formFields = new HashMap<>();

        for (String field : fields) {
            formFields.put(field, formData.getOrDefault(field, "DOES NOT EXIST"));
        }

        return formFields;
    }

}
