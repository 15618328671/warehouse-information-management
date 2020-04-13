package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.commons.persistence.BaseService;
import com.zk.warehouse.information.management.domain.TbUser;

/**
 * @author zk
 * @date 2020/4/13-15:45
 */
public interface TbUserService extends BaseService<TbUser> {
    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    int countUsername(String username);
}
