package com.zk.warehouse.information.management.domain;

import com.zk.warehouse.information.management.commons.persistence.BaseEntity;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/22-13:46
 */
public class TbWarehouse extends BaseEntity {
    private String name;
    private String number;
    private Double inventory;
    private String parentId;
    private Boolean isParent;
    private Integer sortOrder;
    //对应的货物实体类
    private TbCargo tbCargo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public TbCargo getTbCargo() {
        return tbCargo;
    }

    public void setTbCargo(TbCargo tbCargo) {
        this.tbCargo = tbCargo;
    }
}
