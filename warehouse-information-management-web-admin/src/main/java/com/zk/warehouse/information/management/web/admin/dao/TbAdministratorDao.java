package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.domain.TbAdministrator;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2020/1/18-14:40
 */
@Repository
public interface TbAdministratorDao {
    /**
     * 查询管理员表全部信息
     * @return
     */
    List<TbAdministrator> selectAll();

    /**
     * 新增
     * @param tbAdministrator
     */
    void insert(TbAdministrator tbAdministrator);

    /**
     * 改
     * @param tbAdministrator
     */
    void update(TbAdministrator tbAdministrator);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    TbAdministrator getByUsername(String username);

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    TbAdministrator getById(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params 传两个参数 start/记录数开始的位置 length/每页的记录数
     * @return
     */
    List<TbAdministrator> page(Map<String,Object> params);

    /**
     * 查询总数
     * @return
     */
    int count(TbAdministrator tbAdministrator);
}
