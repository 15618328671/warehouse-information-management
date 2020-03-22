package com.zk.warehouse.information.management.domain;

import com.zk.warehouse.information.management.commons.persistence.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zk
 * @date 2020/2/22-13:46
 */
@Data
public class TbWarehouse extends BaseEntity {
    private String name;
    private String number;
    private Double inventory;
    private String parentId;
    private Boolean isParent;
    private Integer sortOrder;
    private Double entryQuantity;
    private Date entryTime;
    private Double deliveryQuantity;
    private Date deliveryTime;
    private Double capacity;
    //对应的货物实体类
    private TbCargo tbCargo;

}
