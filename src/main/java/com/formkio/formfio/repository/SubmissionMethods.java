package com.formkio.formfio.repository;

import com.formkio.formfio.dto.SubmissionDTO;

import java.util.List;

public interface SubmissionMethods {
    List<String> getFields(String formId);
    void createNewFormSubmission(SubmissionDTO submissionDTO) ;
}
