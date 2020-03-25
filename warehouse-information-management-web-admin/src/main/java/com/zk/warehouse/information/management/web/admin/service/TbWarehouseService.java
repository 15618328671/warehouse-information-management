package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
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
     * 根据id获取仓库信息
     * @param id
     * @return
     */
    TbWarehouse getById(Long id);

    /**
     * 保存仓库信息
     * @param tbWarehouse
     * @return
     */
    BaseResult save(TbWarehouse tbWarehouse);

    /**
     * 获取全部仓库名称
     * @return
     */
    List<String> getName();

    /**
     * 获取仓库名称的对应编号
     * @return
     */
    String getNumberByName(String name);

    /**
     * 根据仓库名称删除信息
     * @param name
     */
    void delete(String name);
}
