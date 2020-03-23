package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.domain.TbWarehouse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/22-13:55
 */
@Repository
public interface TbWarehouseDao {
    /**
     * 查询仓库与货物表全部数据
     * @return
     */
    List<TbWarehouse> selectAll();

    /**
     * 通过id获取仓库信息
     * @param id
     * @return
     */
    TbWarehouse getById(Long id);

    void insert(TbWarehouse tbWarehouse);

    void update(TbWarehouse tbWarehouse);

    /**
     * 获取全部仓库名称
     * @return
     */
    List<String> getName();

    /**
     * 仓库名是否存在
     * @param tbWarehouse
     * @return
     */
    int countName(TbWarehouse tbWarehouse);

    /**
     * 仓库编号是否存在
     * @param tbWarehouse
     * @return
     */
    int countNumber(TbWarehouse tbWarehouse);

    /**
     * 获取仓库名称的相应编号
     * @return
     */
    String getNumberByName(String name);
}
