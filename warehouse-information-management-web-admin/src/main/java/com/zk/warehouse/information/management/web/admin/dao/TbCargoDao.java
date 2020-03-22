package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.commons.persistence.BaseDao;
import com.zk.warehouse.information.management.domain.TbCargo;
import org.springframework.stereotype.Repository;

/**
 * @author zk
 * @date 2020/2/29-14:30
 */
@Repository
public interface TbCargoDao extends BaseDao<TbCargo> {
    /**
     * 查询货物名是否存在
     * @param name
     * @return
     */
    int countName(String name);

    /**
     * 查询货物编号是否存在
     * @param number
     * @return
     */
    int countNumber(String number);
}