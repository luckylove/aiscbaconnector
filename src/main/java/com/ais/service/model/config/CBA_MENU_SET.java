package com.ais.service.model.config;


public class CBA_MENU_SET {

    private Long menuSetId;
    private String menuSetName;
    private String langId;
    private String description;
    private String phrMenuSet;
    private String menuSetMenuid;

    public Long getMenuSetId() {
        return menuSetId;
    }

    public void setMenuSetId(Long menuSetId) {
        this.menuSetId = menuSetId;
    }

    public String getMenuSetName() {
        return menuSetName;
    }

    public void setMenuSetName(String menuSetName) {
        this.menuSetName = menuSetName;
    }

    public String getLangId() {
        return langId;
    }

    public void setLangId(String langId) {
        this.langId = langId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhrMenuSet() {
        return phrMenuSet;
    }

    public void setPhrMenuSet(String phrMenuSet) {
        this.phrMenuSet = phrMenuSet;
    }

    public String getMenuSetMenuid() {
        return menuSetMenuid;
    }

    public void setMenuSetMenuid(String menuSetMenuid) {
        this.menuSetMenuid = menuSetMenuid;
    }
}