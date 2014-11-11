package com.ais.service.model.config;


public class CBA_ACT {

    private String actId;
    private String starttime;
    private String endtime;
    private String cbaReqId;
    private Long trys;
    private String result;

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
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

    public Long getTrys() {
        return trys;
    }

    public void setTrys(Long trys) {
        this.trys = trys;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}