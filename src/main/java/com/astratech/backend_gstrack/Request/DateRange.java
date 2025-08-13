package com.astratech.backend_gstrack.Request;

import java.sql.Date;

public class DateRange {
    private String npk;
    private Date startDate;
    private Date endDate;


    public DateRange()
    {}

    public DateRange(String npk, Date startDate, Date endDate) {
        this.npk = npk;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getNpk() {
        return npk;
    }

    public void setNpk(String npk) {
        this.npk = npk;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
