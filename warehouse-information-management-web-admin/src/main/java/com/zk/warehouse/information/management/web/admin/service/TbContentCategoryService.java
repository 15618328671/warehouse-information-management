package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.domain.TbContentCategory;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/12-14:29
 */
public interface TbContentCategoryService {
    /**
     * 查询全部信息
     * @return
     */
    List<TbContentCategory> selectAll();
}
