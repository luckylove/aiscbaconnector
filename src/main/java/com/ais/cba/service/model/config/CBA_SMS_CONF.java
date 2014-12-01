package com.ais.cba.service.model.config;


public class CBA_SMS_CONF {

    private Long smsConfId;
    private String serviceId;
    private Long status;
    private String langId;
    private Long statusCount;
    private String enable;
    private String isSms;
    private String smsServiceId;
    private String isGenact;
    private String genactServiceId;

    public Long getSmsConfId() {
        return smsConfId;
    }

    public void setSmsConfId(Long smsConfId) {
        this.smsConfId = smsConfId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public Long getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Long statusCount) {
        this.statusCount = statusCount;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getIsSms() {
        return isSms;
    }

    public void setIsSms(String isSms) {
        this.isSms = isSms;
    }

    public String getSmsServiceId() {
        return smsServiceId;
    }

    public void setSmsServiceId(String smsServiceId) {
        this.smsServiceId = smsServiceId;
    }

    public String getIsGenact() {
        return isGenact;
    }

    public void setIsGenact(String isGenact) {
        this.isGenact = isGenact;
    }

    public String getGenactServiceId() {
        return genactServiceId;
    }

    public void setGenactServiceId(String genactServiceId) {
        this.genactServiceId = genactServiceId;
    }
}