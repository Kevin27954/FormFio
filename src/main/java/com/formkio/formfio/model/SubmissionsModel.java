package com.formkio.formfio.model;

import java.time.LocalDateTime;

public class SubmissionsModel {
    public int id;
    public String data;
    public String source;
    public String ipAddr;
    public String endpoint;
    public LocalDateTime createdAt;

    public SubmissionsModel() {
    }

    public SubmissionsModel(String data, String source, String ipAddr, String endpoint) {
        this.data = data;
        this.source = source;
        this.ipAddr = ipAddr;
        this.endpoint = endpoint;
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

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
