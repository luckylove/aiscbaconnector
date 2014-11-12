package com.ais.cba.service.model.config;


public class CBA_ACT_DETAIL {

    private String actdId;
    private String starttime;
    private String endtime;
    private String cbaReqId;
    private String actId;
    private Long actTypeId;
    private String actParam;
    private String result;

    public String getActdId() {
        return actdId;
    }

    public void setActdId(String actdId) {
        this.actdId = actdId;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCbaReqId() {
        return cbaReqId;
    }

    public void setCbaReqId(String cbaReqId) {
        this.cbaReqId = cbaReqId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public Long getActTypeId() {
        return actTypeId;
    }

    public void setActTypeId(Long actTypeId) {
        this.actTypeId = actTypeId;
    }

    public String getActParam() {
        return actParam;
    }

    public void setActParam(String actParam) {
        this.actParam = actParam;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}