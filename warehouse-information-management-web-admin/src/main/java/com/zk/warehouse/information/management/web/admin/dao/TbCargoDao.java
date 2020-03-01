package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.domain.TbCargo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/29-14:30
 */
@Repository
public interface TbCargoDao {
    /**
     * 查询全部货物
     * @return
     */
    List<TbCargo> selectAll();

    /**
     * 根据货物id查询
     * @return
     */
    TbCargo getById();
}
