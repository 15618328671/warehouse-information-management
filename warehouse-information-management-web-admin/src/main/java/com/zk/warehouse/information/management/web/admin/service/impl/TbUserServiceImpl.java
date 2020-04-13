package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbUser;
import com.zk.warehouse.information.management.web.admin.dao.TbUserDao;
import com.zk.warehouse.information.management.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zk
 * @date 2020/4/13-15:46
 */
@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return null;
    }

    @Override
    public BaseResult save(TbUser entity) {
        return null;
    }

    @Override
    public void insert(TbUser entity) {
        tbUserDao.insert(entity);
    }

    @Override
    public TbUser getById(Long id) {
        return null;
    }

    @Override
    public void deleteMulti(String[] ids) {

    }

    @Override
    public PageInfo<TbUser> page(int start, int length, int draw, TbUser entity) {
        return null;
    }

    @Override
    public int count(TbUser entity) {
        return 0;
    }

    @Override
    public int countUsername(String username) {
        return tbUserDao.haveUsername(username);
    }
}
