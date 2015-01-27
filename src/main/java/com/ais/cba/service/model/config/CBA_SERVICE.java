package com.ais.cba.service.model.config;


public class CBA_SERVICE {

    private String serviceId;
    private String serviceName;
    private String description;
    private Long sla;
    private Long menuSetId;
    private Long maxTry;
    private Long tryInterval;
    private String agentSplit;
    private String phrAgentGreeting;
    private String phrCustGreeting;
    private String phrCustEnding;
    private String phrCustError;

    public String getPhrCustEnding() {
        return phrCustEnding;
    }

    public void setPhrCustEnding(String phrCustEnding) {
        this.phrCustEnding = phrCustEnding;
    }

    public String getPhrCustError() {
        return phrCustError;
    }

    public void setPhrCustError(String phrCustError) {
        this.phrCustError = phrCustError;
    }

    public String getAgentSplit() {
        return agentSplit;
    }

    public void setAgentSplit(String agentSplit) {
        this.agentSplit = agentSplit;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public String getPhrAgentGreeting() {
        return phrAgentGreeting;
    }

    public void setPhrAgentGreeting(String phrAgentGreeting) {
        this.phrAgentGreeting = phrAgentGreeting;
    }

    public String getPhrCustGreeting() {
        return phrCustGreeting;
    }

    public void setPhrCustGreeting(String phrCustGreeting) {
        this.phrCustGreeting = phrCustGreeting;
    }
}