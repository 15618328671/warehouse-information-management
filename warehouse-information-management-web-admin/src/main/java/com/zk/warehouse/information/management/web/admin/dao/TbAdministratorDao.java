package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.commons.persistence.BaseDao;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import org.springframework.stereotype.Repository;

/**
 * @author zk
 * @date 2020/1/18-14:40
 */
@Repository
public interface TbAdministratorDao extends BaseDao<TbAdministrator> {
    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    TbAdministrator getByUsername(String username);

    /**
     * 用户名是否存在
     * @param tbAdministrator
     * @return
     */
    int haveUsername(TbAdministrator tbAdministrator);
    /**
     * 手机号是否存在
     * @param tbAdministrator
     * @return
     */
    int havePhone(TbAdministrator tbAdministrator);

    /**
     * 电子邮箱是否存在
     * @param tbAdministrator
     * @return
     */
    int haveEmail(TbAdministrator tbAdministrator);
}
