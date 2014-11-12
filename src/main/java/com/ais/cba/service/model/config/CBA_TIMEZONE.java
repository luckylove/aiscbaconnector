package com.ais.cba.service.model.config;


public class CBA_TIMEZONE {

    private String timezoneId;
    private String timezoneName;
    private Long gmtDiff;

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public Long getGmtDiff() {
        return gmtDiff;
    }

    public void setGmtDiff(Long gmtDiff) {
        this.gmtDiff = gmtDiff;
    }
}