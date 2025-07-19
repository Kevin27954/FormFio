package com.formkio.formfio.dto;

public class SubmissionDTO {

    private String data;
    private String source;
    private String ip_addr;
    private String endpoint;

    public SubmissionDTO(String data, String source, String ip_addr, String endpoint) {
        this.data = data;
        this.source = source;
        this.ip_addr = ip_addr;
        this.endpoint = endpoint;
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

    public String getIp_addr() {
        return ip_addr;
    }

    public void setIp_addr(String ip_addr) {
        this.ip_addr = ip_addr;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "SubmissionDTO{" +
                "data='" + data + '\'' +
                ", source='" + source + '\'' +
                ", ip_addr='" + ip_addr + '\'' +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
