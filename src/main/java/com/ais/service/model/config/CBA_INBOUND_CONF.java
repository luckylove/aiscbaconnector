package com.ais.service.model.config;


public class CBA_INBOUND_CONF {

    private Long idx;
    private String langId;
    private String networkType;
    private String subnetType;
    private String mobSegment;
    private String arpuRange;
    private String xferVdn;
    private String cbaXferVdn;
    private String cbaInboundVdn;
    private String serviceId;
    private String enable;
    private String startTime;
    private String endTime;
    private Long maxActiveReq;
    private String priority;
    private Long maxConcurrentCall;
    private Long duplicateInterval;
    private Long cbStartBuffer;


    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getSubnetType() {
        return subnetType;
    }

    public void setSubnetType(String subnetType) {
        this.subnetType = subnetType;
    }

    public String getMobSegment() {
        return mobSegment;
    }

    public void setMobSegment(String mobSegment) {
        this.mobSegment = mobSegment;
    }

    public String getArpuRange() {
        return arpuRange;
    }

    public void setArpuRange(String arpuRange) {
        this.arpuRange = arpuRange;
    }

    public String getXferVdn() {
        return xferVdn;
    }

    public void setXferVdn(String xferVdn) {
        this.xferVdn = xferVdn;
    }

    public String getCbaXferVdn() {
        return cbaXferVdn;
    }

    public void setCbaXferVdn(String cbaXferVdn) {
        this.cbaXferVdn = cbaXferVdn;
    }

    public String getCbaInboundVdn() {
        return cbaInboundVdn;
    }

    public void setCbaInboundVdn(String cbaInboundVdn) {
        this.cbaInboundVdn = cbaInboundVdn;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getMaxActiveReq() {
        return maxActiveReq;
    }

    public void setMaxActiveReq(Long maxActiveReq) {
        this.maxActiveReq = maxActiveReq;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Long getMaxConcurrentCall() {
        return maxConcurrentCall;
    }

    public void setMaxConcurrentCall(Long maxConcurrentCall) {
        this.maxConcurrentCall = maxConcurrentCall;
    }

    public Long getDuplicateInterval() {
        return duplicateInterval;
    }

    public void setDuplicateInterval(Long duplicateInterval) {
        this.duplicateInterval = duplicateInterval;
    }

    public Long getCbStartBuffer() {
        return cbStartBuffer;
    }

    public void setCbStartBuffer(Long cbStartBuffer) {
        this.cbStartBuffer = cbStartBuffer;
    }
}