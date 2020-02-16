package com.zk.warehouse.information.management.web.admin.service;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbAdministrator;

import java.util.List;

/**
 * @author zk
 * @date 2020/1/18-14:45
 */
public interface TbAdministratorService {
    /**
     * 查询所有管理员信息
     * @return
     */
    List<TbAdministrator> selectAll();

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    TbAdministrator login(String username,String password);

    /**
     * 保存信息
     * @param tbAdministrator
     */
    BaseResult save(TbAdministrator tbAdministrator);

    /**
     * 添加用户
     * @param tbAdministrator
     */
    void insert(TbAdministrator tbAdministrator);

    /**
     * 根据用户id查找用户信息
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
     * @param start 记录开始位置
     * @param length 每页记录数
     * @param draw
     * @param tbAdministrator
     * @return
     */
    PageInfo<TbAdministrator> page(int start, int length,int draw,TbAdministrator tbAdministrator);

    /**
     * 查询总数
     * @param tbAdministrator
     * @return
     */
    int count(TbAdministrator tbAdministrator);
}
