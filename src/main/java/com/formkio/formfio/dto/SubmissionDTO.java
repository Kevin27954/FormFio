package com.formkio.formfio.dto;

import java.time.LocalDateTime;

public class SubmissionDTO {

    private String data;
    private String source;
    private String endpoint;
    private LocalDateTime createdDate;

    public SubmissionDTO(String data, String source, String endpoint, LocalDateTime createdDate) {
        this.data = data;
        this.source = source;
        this.endpoint = endpoint;
        this.createdDate = createdDate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "SubmissionDTO{" +
                "data='" + data + '\'' +
                ", source='" + source + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
