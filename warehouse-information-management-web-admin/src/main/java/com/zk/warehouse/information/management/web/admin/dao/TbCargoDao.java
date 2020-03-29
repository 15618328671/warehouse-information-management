package com.zk.warehouse.information.management.web.admin.dao;

import com.zk.warehouse.information.management.commons.persistence.BaseDao;
import com.zk.warehouse.information.management.domain.TbCargo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zk
 * @date 2020/2/29-14:30
 */
@Repository
public interface TbCargoDao extends BaseDao<TbCargo> {
    /**
     * 查询货物名是否存在
     * @param tbCargo
     * @return
     */
    int countName(TbCargo tbCargo);

    /**
     * 查询货物编号是否存在
     * @param tbCargo
     * @return
     */
    int countNumber(TbCargo tbCargo);

    /**
     * 根据编号查询名称
     * @param number
     * @return
     */
    String getNameByNumber(String number);

    /**
     * 根据编号获取现存货
     * @param number
     * @return
     */
    Double getInventoryByNumber(String number);

    /**
     * 根据货物名称更改入库信息
     * @param tbCargo
     * @param name
     */
    void updateEntryByName(@Param("tbCargo") TbCargo tbCargo,@Param("name") String name);

    /**
     * 根据货物名称更改入库信息
     * @param tbCargo
     * @param name
     */
    void updateDeliveryByName(@Param("tbCargo") TbCargo tbCargo,@Param("name") String name);

    /**
     * 根据所属仓库查询该仓库现存货量
     * @param parentId
     * @return
     */
    Double sumInventory(String parentId);

    /**
     * 查看所属仓库下是否有货物
     * @param parentId
     * @return
     */
    int countByParentId(String parentId);

    /**
     * 删除该仓库所有货物
     * @param parentId
     */
    void deleteByParentId(String parentId);
}
