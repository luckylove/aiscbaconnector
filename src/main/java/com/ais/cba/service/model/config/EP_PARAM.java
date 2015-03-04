package com.ais.cba.service.model.config;


public class EP_PARAM {

    private Long idx;
    private String paramName;
    private String paramValue;
    private String app;
    private String paramOut1;
    private String paramOut2;
    private String networkType;
    private String subnetworkType;
    private String enable;

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getParamOut1() {
        return paramOut1;
    }

    public void setParamOut1(String paramOut1) {
        this.paramOut1 = paramOut1;
    }

    public String getParamOut2() {
        return paramOut2;
    }

    public void setParamOut2(String paramOut2) {
        this.paramOut2 = paramOut2;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getSubnetworkType() {
        return subnetworkType;
    }

    public void setSubnetworkType(String subnetworkType) {
        this.subnetworkType = subnetworkType;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}