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

    /**
     * 获取全部仓库名称
     * @return
     */
    List<String> getName();

    /**
     * 获取仓库名称的相应编号
     * @return
     */
    String getNumberByName(String name);
}
