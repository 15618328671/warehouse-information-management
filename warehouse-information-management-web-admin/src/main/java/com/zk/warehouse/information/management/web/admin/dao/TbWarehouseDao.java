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
    //查询仓库与货物表全部数据
    List<TbWarehouse> selectAll();
}
