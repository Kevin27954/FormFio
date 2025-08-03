package com.formkio.formfio.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;

public class UsersModel {
    // Just use default shit, no need for @Entity and the likes bb.
    int id;
    String email;
    int isReferred;
    int accountPlan;
    LocalDateTime freeTrial;

    public UsersModel() {
        this.isReferred = 0;
        this.accountPlan = 0;
        this.freeTrial = LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsReferred() {
        return isReferred;
    }

    public void setIsReferred(int isReferred) {
        this.isReferred = isReferred;
    }

    public int getAccountPlan() {
        return accountPlan;
    }

    public void setAccountPlan(int accountPlan) {
        this.accountPlan = accountPlan;
    }

    public LocalDateTime getFreeTrial() {
        return freeTrial;
    }

    public void setFreeTrial(LocalDateTime freeTrial) {
        this.freeTrial = freeTrial;
    }

    @Override
    public String toString() {
        return "UsersModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", isReferred=" + isReferred +
                ", accountPlan=" + accountPlan +
                ", freeTrial=" + freeTrial +
                '}';
    }
}
