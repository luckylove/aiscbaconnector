package com.ais.service.model.config;


public class CBA_SERVICE {

    private String serviceId;
    private String langId;
    private String serviceName;
    private String description;
    private Long sla;
    private Long menuSetId;
    private Long maxTry;
    private Long tryInterval;
    private String smsIdSucc;
    private String genactIdSucc;
    private String smsIdFail;
    private String genactIdFail;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSla() {
        return sla;
    }

    public void setSla(Long sla) {
        this.sla = sla;
    }

    public Long getMenuSetId() {
        return menuSetId;
    }

    public void setMenuSetId(Long menuSetId) {
        this.menuSetId = menuSetId;
    }

    public Long getMaxTry() {
        return maxTry;
    }

    public void setMaxTry(Long maxTry) {
        this.maxTry = maxTry;
    }

    public Long getTryInterval() {
        return tryInterval;
    }

    public void setTryInterval(Long tryInterval) {
        this.tryInterval = tryInterval;
    }

    public String getSmsIdSucc() {
        return smsIdSucc;
    }

    public void setSmsIdSucc(String smsIdSucc) {
        this.smsIdSucc = smsIdSucc;
    }

    public String getGenactIdSucc() {
        return genactIdSucc;
    }

    public void setGenactIdSucc(String genactIdSucc) {
        this.genactIdSucc = genactIdSucc;
    }

    public String getSmsIdFail() {
        return smsIdFail;
    }

    public void setSmsIdFail(String smsIdFail) {
        this.smsIdFail = smsIdFail;
    }

    public String getGenactIdFail() {
        return genactIdFail;
    }

    public void setGenactIdFail(String genactIdFail) {
        this.genactIdFail = genactIdFail;
    }
}