package com.formkio.formfio.dto;

import java.util.Calendar;
import java.util.Date;

public class RateDTO {
    int currSubmission;
    Date enddate;

    public RateDTO() {
        this.currSubmission = 0;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        this.enddate = cal.getTime();
    }

    public void incCurrSubmission() {
        this.currSubmission++;
    }

    public int getCurrSubmission() {
        return currSubmission;
    }

    public Date getEnddate() {
        return enddate;
    }
}