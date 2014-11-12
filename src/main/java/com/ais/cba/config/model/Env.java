package com.ais.cba.config.model;

public class Env {

    private String name;

    private GetConfig getConfig;

    private LogConfig logConfig;

    public LogConfig getLogConfig() {
        return logConfig;
    }

    public void setLogConfig(LogConfig logConfig) {
        this.logConfig = logConfig;
    }

    public GetConfig getGetConfig() {
        return getConfig;
    }

    public void setGetConfig(GetConfig getConfig) {
        this.getConfig = getConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
