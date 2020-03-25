package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.validator.BeanValidator;
import com.zk.warehouse.information.management.domain.TbWarehouse;
import com.zk.warehouse.information.management.web.admin.dao.TbWarehouseDao;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zk
 * @date 2020/2/22-13:56
 */
@Service
public class TbWarehouseServiceImpl implements TbWarehouseService {
    @Autowired
    private TbWarehouseDao tbWarehouseDao;

    @Override
    public List<TbWarehouse> selectAll() {
        return tbWarehouseDao.selectAll();
    }

    @Override
    public TbWarehouse getById(Long id) {
        return tbWarehouseDao.getById(id);
    }

    @Override
    public BaseResult save(TbWarehouse tbWarehouse) {
        String validator = BeanValidator.validator(tbWarehouse);
        //validator验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        else {
            BaseResult baseResult = checkTbWarehouse(tbWarehouse);
            //通过重复性验证
            if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
                tbWarehouse.setUpdated(new Date());
                //新增仓库
                if (tbWarehouse.getId() == null){
                    tbWarehouse.setIsParent(true);
                    tbWarehouse.setParentId("0");
                    tbWarehouse.setCreated(new Date());
                    tbWarehouseDao.insert(tbWarehouse);
                }
                //编辑仓库
                else {
                    tbWarehouseDao.update(tbWarehouse);
                }
                return BaseResult.success("保存仓库信息成功");
            }
            //重复性验证失败
            else {
                return baseResult;
            }
        }
    }

    @Override
    public List<String> getName() {
        return tbWarehouseDao.getName();
    }

    @Override
    public String getNumberByName(String name) {
        return tbWarehouseDao.getNumberByName(name);
    }

    @Override
    public void delete(String name) {
        tbWarehouseDao.delete(name);
    }

    /**
     * 重复性验证
     * @param tbWarehouse
     * @return
     */
    private BaseResult checkTbWarehouse(TbWarehouse tbWarehouse) {
        BaseResult baseResult = BaseResult.success();
        if (tbWarehouseDao.countName(tbWarehouse)>0){
            baseResult = BaseResult.fail("仓库已存在,请重新输入");
        }
        if (tbWarehouseDao.countNumber(tbWarehouse)>0){
            baseResult = BaseResult.fail("仓库编号已存在,请重新输入");
        }
        return baseResult;
    }
}
