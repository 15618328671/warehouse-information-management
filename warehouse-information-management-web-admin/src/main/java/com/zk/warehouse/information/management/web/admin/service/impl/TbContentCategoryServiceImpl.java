package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.domain.TbContentCategory;
import com.zk.warehouse.information.management.web.admin.dao.TbContentCategoryDao;
import com.zk.warehouse.information.management.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/12-14:29
 */
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }
}
