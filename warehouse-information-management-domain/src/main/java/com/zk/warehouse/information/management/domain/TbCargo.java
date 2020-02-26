package com.zk.warehouse.information.management.domain;

import com.zk.warehouse.information.management.commons.persistence.BaseEntity;

/**
 * @author zk
 * @date 2020/2/22-16:32
 */
public class TbCargo extends BaseEntity {
    private String name;
    private String number;
    private Boolean isParent;
    private String parentId;
    private Double inventory;
    private Integer sortOrder;

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

    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
