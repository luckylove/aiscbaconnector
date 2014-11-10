package com.ais.service.model.config;

import java.util.Date;

/**
 * User: son.nguyen
 * Date: 11/10/2014
 * Time: 10:05 PM
 */
public class CBA_BLACKLIST {

    private Long idx;
    private String phone;
    private Date createDate;

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
