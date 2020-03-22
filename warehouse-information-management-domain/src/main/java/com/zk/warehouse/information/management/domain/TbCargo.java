package com.zk.warehouse.information.management.domain;

import com.zk.warehouse.information.management.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zk
 * @date 2020/2/22-16:32
 */
@Data
public class TbCargo extends BaseEntity {
    @NotBlank(message = "货物名不能为空")
    private String name;
    @NotBlank(message = "货物编号不能为空")
    private String number;
    private Boolean isParent;
    @NotBlank(message = "所属仓库不能为空")
    private String parentId;
    @NotNull(message = "货物总量不能为空")
    private Double inventory;
    @NotNull(message = "货物名排序不能为空")
    private Integer sortOrder;
    private Double entryQuantity;
    private Date entryTime;
    private Double deliveryQuantity;
    private Date deliveryTime;
}
