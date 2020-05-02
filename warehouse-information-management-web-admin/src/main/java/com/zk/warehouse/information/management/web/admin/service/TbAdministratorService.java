package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.commons.persistence.BaseService;
import com.zk.warehouse.information.management.domain.TbAdministrator;

/**
 * @author zk
 * @date 2020/1/18-14:45
 */
public interface TbAdministratorService extends BaseService<TbAdministrator> {
    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    TbAdministrator login(String username,String password);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    TbAdministrator getByUsername(String username);
}
