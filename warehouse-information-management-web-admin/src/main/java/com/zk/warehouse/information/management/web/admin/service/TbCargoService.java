package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.domain.TbCargo;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/29-14:35
 */
public interface TbCargoService {
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
