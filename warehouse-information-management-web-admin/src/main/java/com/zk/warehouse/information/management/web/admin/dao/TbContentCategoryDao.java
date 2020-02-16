package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/12-14:27
 */
@Repository
public interface TbContentCategoryDao {
    /**
     * 查询全部信息
     * @return
     */
    List<TbContentCategory> selectAll();
}
