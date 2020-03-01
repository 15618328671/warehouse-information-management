package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.web.admin.dao.TbCargoDao;
import com.zk.warehouse.information.management.web.admin.service.TbCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/29-14:35
 */
@Service
public class TbCargoServiceImpl implements TbCargoService {
    @Autowired
    private TbCargoDao tbCargoDao;

    @Override
    public List<TbCargo> selectAll() {
        return tbCargoDao.selectAll();
    }

    @Override
    public TbCargo getById() {
        return tbCargoDao.getById();
    }
}
