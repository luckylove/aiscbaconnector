package com.ais.cba.service.model.config;

/**
 * User: son.nguyen
 * Date: 1/27/2015
 * Time: 10:19 PM
 */
public class CBA_SERVICE_LASTMENU {

    private Long idx;
    private String serviceId;
    private String ivrService;
    private String description;
    private String ivrLastmenu;

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getIvrService() {
        return ivrService;
    }

    public void setIvrService(String ivrService) {
        this.ivrService = ivrService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIvrLastmenu() {
        return ivrLastmenu;
    }

    public void setIvrLastmenu(String ivrLastmenu) {
        this.ivrLastmenu = ivrLastmenu;
    }
}
