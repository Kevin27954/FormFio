package com.formkio.formfio.model;

import com.formkio.formfio.repository.UserFormRepo;

import java.util.Map;

/**
 * TODO: This class really should just be some getters and setters instead
 */

public class FormSubmissionModel {
    protected final UserFormRepo userFormRepo;
    protected String type;
    protected Map<String, String> formData;

    public FormSubmissionModel(UserFormRepo userFormRepo, String type, Map<String, String> formData) {
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
