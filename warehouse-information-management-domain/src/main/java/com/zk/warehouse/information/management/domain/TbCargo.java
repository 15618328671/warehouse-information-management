package com.zk.warehouse.information.management.domain;

import com.zk.warehouse.information.management.commons.persistence.BaseEntity;

/**
 * @author zk
 * @date 2020/2/22-16:32
 */
public class TbCargo extends BaseEntity {
    private String cargoName;
    private String cargoNumber;
    private Double cargoInventory;
    private String parentId;
    private Boolean isParent;
    private Integer sortOrder;

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCargoNumber() {
        return cargoNumber;
    }

    public void setCargoNumber(String cargoNumber) {
        this.cargoNumber = cargoNumber;
    }

    public Double getCargoInventory() {
        return cargoInventory;
    }

    public void setCargoInventory(Double cargoInventory) {
        this.cargoInventory = cargoInventory;
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
}
