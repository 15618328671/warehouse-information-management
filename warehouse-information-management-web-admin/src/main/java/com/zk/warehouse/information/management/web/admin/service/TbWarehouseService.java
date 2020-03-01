package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.domain.TbWarehouse;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/22-13:56
 */
public interface TbWarehouseService {
    /**
     * 查询仓库与货物表全部数据
     * @return
     */
    List<TbWarehouse> selectAll();

    /**
     * 获取全部仓库名称
     * @return
     */
    List<String> getName();
}
