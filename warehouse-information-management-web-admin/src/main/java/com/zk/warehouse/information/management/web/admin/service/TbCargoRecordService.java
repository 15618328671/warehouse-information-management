package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.persistence.BaseService;
import com.zk.warehouse.information.management.domain.TbCargoRecord;

import java.util.List;

/**
 * @author zk
 * @date 2020/3/26-15:03
 */
public interface TbCargoRecordService extends BaseService<TbCargoRecord> {
    /**
     * 根据操作者查询数据
     * @param handlers
     * @return
     */
    List<TbCargoRecord> selectByHandlers(String handlers);

    /**
     * 更新评论
     * @param tbCargoRecord
     * @return
     */
    void update(TbCargoRecord tbCargoRecord);
}
