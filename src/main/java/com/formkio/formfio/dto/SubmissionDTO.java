package com.formkio.formfio.dto;

import java.time.LocalDateTime;

public class SubmissionDTO implements Comparable<SubmissionDTO> {

    private int id;
    private String data;
    private String source;
    private String endpoint;
    private LocalDateTime createdDate;

    public SubmissionDTO(int id, String data, String source, String endpoint, LocalDateTime createdDate) {
        this.id = id;
        this.data = data;
        this.source = source;
        this.endpoint = endpoint;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public int compareTo(SubmissionDTO sub) {
        return this.createdDate.compareTo(sub.createdDate);
    }

    @Override
    public String toString() {
        return "SubmissionDTO{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", source='" + source + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
