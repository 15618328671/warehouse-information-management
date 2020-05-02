package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.commons.persistence.BaseService;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.domain.TbUser;

/**
 * @author zk
 * @date 2020/4/13-15:45
 */
public interface TbUserService extends BaseService<TbUser> {
    /**
     * 用户名是否存在
     * @param tbUser
     * @return
     */
    int countUsername(TbUser tbUser);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    TbUser login(String username, String password);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    TbUser getByUsername(String username);
}
