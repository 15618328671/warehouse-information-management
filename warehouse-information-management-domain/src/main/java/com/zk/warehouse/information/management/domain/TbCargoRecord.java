package com.zk.warehouse.information.management.domain;

import com.zk.warehouse.information.management.commons.persistence.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author zk
 * @date 2020/3/26-14:53
 */
@Data
public class TbCargoRecord extends BaseEntity {
    private String name;
    private String number;
    private String parentId;
    private Double inventory;
    private Double entryQuantity;
    private Date entryTime;
    private Double deliveryQuantity;
    private Date deliveryTime;
}
