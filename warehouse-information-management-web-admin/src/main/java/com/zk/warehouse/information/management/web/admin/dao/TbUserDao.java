package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.commons.persistence.BaseDao;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * @author zk
 * @date 2020/4/13-15:45
 */
@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 用户名是否存在
     * @param tbUser
     * @return
     */
    int haveUsername(TbUser tbUser);

    /**
     * 手机号是否存在
     * @param tbUser
     * @return
     */
    int havePhone(TbUser tbUser);

    /**
     * 电子邮箱是否存在
     * @param tbUser
     * @return
     */
    int haveEmail(TbUser tbUser);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    TbUser getByUsername(String username);
}
