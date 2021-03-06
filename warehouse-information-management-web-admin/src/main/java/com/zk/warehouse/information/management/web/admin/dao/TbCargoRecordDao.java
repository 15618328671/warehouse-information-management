package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.commons.persistence.BaseDao;
import com.zk.warehouse.information.management.domain.TbCargoRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zk
 * @date 2020/3/26-15:01
 */
@Repository
public interface TbCargoRecordDao extends BaseDao<TbCargoRecord> {
    /**
     * 根据操作者查询数据
     * @param handlers
     * @return
     */
    List<TbCargoRecord> selectByHandlers(String handlers);
}
