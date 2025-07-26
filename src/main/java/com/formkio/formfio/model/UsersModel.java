package com.formkio.formfio.model;

public class UsersModel {
    // Just use default shit, no need for @Entity and the likes bb.
    int id;
    String email;
    int isReferred;
    int accountPlan;
    int freeTrail;

    public UsersModel() {
        this.isReferred = 0;
        this.accountPlan = 0;
        this.freeTrail = 0;
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

    public int getFreeTrail() {
        return freeTrail;
    }

    public void setFreeTrail(int freeTrail) {
        this.freeTrail = freeTrail;
    }

    @Override
    public String toString() {
        return "UsersModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", isReferred=" + isReferred +
                ", accountPlan=" + accountPlan +
                ", freeTrail=" + freeTrail +
                '}';
    }
}
