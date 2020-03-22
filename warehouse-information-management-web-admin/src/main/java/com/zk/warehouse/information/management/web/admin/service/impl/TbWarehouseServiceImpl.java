package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.domain.TbWarehouse;
import com.zk.warehouse.information.management.web.admin.dao.TbWarehouseDao;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/22-13:56
 */
@Service
public class TbWarehouseServiceImpl implements TbWarehouseService {
    @Autowired
    private TbWarehouseDao tbWarehouseDao;

    @Override
    public List<TbWarehouse> selectAll() {
        return tbWarehouseDao.selectAll();
    }

    @Override
    public TbWarehouse getById(Long id) {
        return tbWarehouseDao.getById(id);
    }

    @Override
    public List<String> getName() {
        return tbWarehouseDao.getName();
    }

    @Override
    public String getNumberByName(String name) {
        return tbWarehouseDao.getNumberByName(name);
    }


}
