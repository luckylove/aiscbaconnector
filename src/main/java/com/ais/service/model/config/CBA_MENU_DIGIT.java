package com.ais.service.model.config;


public class CBA_MENU_DIGIT {

    private Long idx;
    private Long menuSetId;
    private String digit;
    private String description;
    private Long cbDelay;
    private String phrDigit;
    private String digitMenuid;


    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public Long getMenuSetId() {
        return menuSetId;
    }

    public void setMenuSetId(Long menuSetId) {
        this.menuSetId = menuSetId;
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCbDelay() {
        return cbDelay;
    }

    public void setCbDelay(Long cbDelay) {
        this.cbDelay = cbDelay;
    }

    public String getPhrDigit() {
        return phrDigit;
    }

    public void setPhrDigit(String phrDigit) {
        this.phrDigit = phrDigit;
    }

    public String getDigitMenuid() {
        return digitMenuid;
    }

    public void setDigitMenuid(String digitMenuid) {
        this.digitMenuid = digitMenuid;
    }
}