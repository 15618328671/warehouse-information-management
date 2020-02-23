package com.zk.warehouse.information.management.domain;

import com.zk.warehouse.information.management.commons.persistence.BaseEntity;

/**
 * @author zk
 * @date 2020/2/22-13:46
 */
public class TbWarehouse extends BaseEntity {
    private String warehouseName;
    private String warehouseNumber;
    private Double warehouseInventory;
    private String parentId;
    private Boolean isParent;
    private Integer sortOrder;
    //对应的货物实体类
    private TbCargo tbCargo;

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(String warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public Double getWarehouseInventory() {
        return warehouseInventory;
    }

    public void setWarehouseInventory(Double warehouseInventory) {
        this.warehouseInventory = warehouseInventory;
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
