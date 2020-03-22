package com.zk.warehouse.information.management.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 所有数据访问层的基类
 */
public interface BaseDao<T extends BaseEntity> {
    /**
     * 查询管理员表全部信息
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     * @param entity
     */
    void insert(T entity);

    /**
     * 修改
     * @param entity
     */
    void update(T entity);

    /**
     * 根据id获取信息
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
     * @param params 传两个参数 start/记录数开始的位置 length/每页的记录数
     * @return
     */
    List<T> page(Map<String,Object> params);

    /**
     * 查询总数
     * @param entity
     * @return
     */
    int count(T entity);
}
