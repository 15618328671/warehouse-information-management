package com.zk.warehouse.information.management.domain;

import com.zk.warehouse.information.management.commons.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author zk
 * @date 2020/2/22-13:46
 */
@Data
public class TbWarehouse extends BaseEntity {
    @NotBlank(message = "仓库名不能为空")
    private String name;
    @NotBlank(message = "仓库编号不能为空")
    private String number;
    @NotNull(message = "仓库存货量不能为空")
    private Double inventory;
    private String parentId;
    private Boolean isParent;
    @NotNull(message = "仓库名排序不能为空")
    private Integer sortOrder;
    private Double entryQuantity;
    private Date entryTime;
    private Double deliveryQuantity;
    private Date deliveryTime;
    @NotNull(message = "仓库总容量不能为空")
    private Double capacity;
    //对应的货物实体类
    private TbCargo tbCargo;

}
