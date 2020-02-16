package com.zk.warehouse.information.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zk.warehouse.information.management.commons.persistence.BaseEntity;

/**
 * @author zk
 * @date 2020/1/18-14:35
 */
public class TbAdministrator extends BaseEntity {
    private String username;
    private String password;
    private String phone;
    private String email;
    private Boolean isRemember;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getRemember() {
        return isRemember;
    }

    public void setRemember(Boolean remember) {
        isRemember = remember;
    }
}
