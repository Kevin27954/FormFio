package com.formkio.formfio.model;

import com.formkio.formfio.repository.SubmissionTable;

import java.util.Map;

/**
 * TODO: This class really should just be some getters and setters instead
 */

public class FormSubmissionModel {
    protected final SubmissionTable userFormRepo;
    protected String type;
    protected Map<String, String> formData;

    public FormSubmissionModel(SubmissionTable userFormRepo, String type, Map<String, String> formData) {
        this.userFormRepo = userFormRepo;
        this.type = type;
        this.formData = formData;
    }

    /**
     * The default implementation only returns `formData`.
     */
    public Map<String, String> extractFields() {
        return formData;
    }

}
