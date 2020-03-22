package com.zk.warehouse.information.management.commons.persistence;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;

import java.util.List;

/**
 * 所有业务逻辑层的基类
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 查询所有管理员信息
     * @return
     */
    List<T> selectAll();

    /**
     * 保存信息
     * @param entity
     */
    BaseResult save(T entity);

    /**
     * 添加
     * @param entity
     */
    void insert(T entity);

    /**
     * 根据用户id查找用户信息
     * @param id
     * @return
     */
    T getById(Long id);

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
     * @param entity
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询总数
     * @param entity
     * @return
     */
    int count(T entity);
}
