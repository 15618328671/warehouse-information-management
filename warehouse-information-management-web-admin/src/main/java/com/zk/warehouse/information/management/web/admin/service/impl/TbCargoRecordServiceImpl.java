package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.commons.validator.BeanValidator;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.domain.TbCargoRecord;
import com.zk.warehouse.information.management.domain.TbWarehouse;
import com.zk.warehouse.information.management.web.admin.dao.TbCargoDao;
import com.zk.warehouse.information.management.web.admin.dao.TbCargoRecordDao;
import com.zk.warehouse.information.management.web.admin.dao.TbWarehouseDao;
import com.zk.warehouse.information.management.web.admin.service.TbCargoRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TbCargoRecordServiceImpl implements TbCargoRecordService {
    @Autowired
    private TbCargoRecordDao tbCargoRecordDao;

    @Autowired
    private TbCargoDao tbCargoDao;

    @Autowired
    private TbWarehouseDao tbWarehouseDao;

    @Override
    public List<TbCargoRecord> selectAll() {
        return tbCargoRecordDao.selectAll();
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbCargoRecord tbCargoRecord) {
        String validator = BeanValidator.validator(tbCargoRecord);
        //Validation验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //Validation验证通过
        else {
            BaseResult baseResult = checkTbCargoRecord(tbCargoRecord);
            if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
                TbWarehouse tbWarehouse = new TbWarehouse();
                tbWarehouse.setUpdated(new Date());

                TbCargo tbCargo = new TbCargo();
                tbCargo.setUpdated(new Date());

                tbCargoRecord.setCreated(new Date());
                tbCargoRecord.setUpdated(new Date());

                //所属仓库
                String parentId = tbCargoRecord.getParentId();
                //出入库前所属仓库现存量
                Double nowInventory = tbCargoDao.sumInventory(parentId);
                //出入库操作前存货量
                Double inventory = tbCargoDao.getInventoryByNumber(tbCargoRecord.getNumber());

                //执行入库操作
                if (tbCargoRecord.getEntryQuantity()!=null){
                    //入库的数量
                    Double entryInventory = tbCargoRecord.getEntryQuantity();
                    //入库后的该货物存货量
                    inventory = inventory+entryInventory;
                    //查询所属仓库的最大容量
                    Double maxCapacity = tbWarehouseDao.getCapacity(parentId);

                    //判断是否超过所属仓库最大容量
                    if (maxCapacity>= inventory+nowInventory){
                        tbCargoRecord.setInventory(inventory);
                        tbCargoRecord.setEntryTime(new Date());
                        //新增货物流动信息
                        tbCargoRecordDao.insert(tbCargoRecord);

                        //修改货物信息
                        tbCargo.setInventory(inventory);
                        //货物最新入库数量
                        tbCargo.setEntryQuantity(entryInventory);
                        //货物最新入库时间
                        tbCargo.setEntryTime(new Date());
                        //更新被更新货物的信息
                        tbCargoDao.updateEntryByName(tbCargo,tbCargoRecord.getName());

                        //更新所属仓库信息
                        tbWarehouse.setEntryQuantity(entryInventory);
                        tbWarehouse.setEntryTime(new Date());
                        tbWarehouse.setInventory(nowInventory+entryInventory);
                        tbWarehouseDao.updateEntryByName(tbWarehouse,parentId);
                    }
                    else {
                        return BaseResult.fail("超出仓库容量上限，请重新输入");
                    }
                }
                //出库操作
                else {
                    tbCargoRecord.setDeliveryTime(new Date());
                    //判断出库数量是否大于现存数量
                    if (tbCargoRecord.getDeliveryQuantity()>inventory){
                        return BaseResult.fail("现存货不足，操作失败");
                    }else {
                        //出库数量
                        Double deliveryInventory = tbCargoRecord.getDeliveryQuantity();

                        //出库后的存货量
                        inventory = inventory-deliveryInventory;
                        tbCargoRecord.setInventory(inventory);
                        tbCargoRecord.setDeliveryTime(new Date());
                        tbCargoRecordDao.insert(tbCargoRecord);

                        //修改货物信息
                        tbCargo.setInventory(inventory);
                        //货物最新出库数量
                        tbCargo.setDeliveryQuantity(deliveryInventory);
                        //货物最新出库时间
                        tbCargo.setDeliveryTime(new Date());
                        tbCargoDao.updateDeliveryByName(tbCargo,tbCargoRecord.getName());

                        //更新出库后所属仓库信息
                        tbWarehouse.setDeliveryQuantity(deliveryInventory);
                        tbWarehouse.setDeliveryTime(new Date());
                        tbWarehouse.setInventory(nowInventory-deliveryInventory);
                        tbWarehouseDao.updateDeliveryByName(tbWarehouse,parentId);
                    }
                }
            }
            return BaseResult.success("保存成功");
        }
    }

    @Override
    public void insert(TbCargoRecord entity) {

    }

    @Override
    public TbCargoRecord getById(Long id) {
        return tbCargoRecordDao.getById(id);
    }

    @Override
    public void deleteMulti(String[] ids) {

    }

    @Override
    public PageInfo<TbCargoRecord> page(int start, int length, int draw, TbCargoRecord tbCargoRecord) {
        int count = tbCargoRecordDao.count(tbCargoRecord);
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbCargoRecord", tbCargoRecord);

        PageInfo<TbCargoRecord> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbCargoRecordDao.page(params));
        return pageInfo;
    }

    @Override
    public int count(TbCargoRecord tbCargoRecord) {
        return 0;
    }

    /**
     * 验证货物是否存在以及库存是否合法
     * @param tbCargoRecord
     * @return
     */
    private BaseResult checkTbCargoRecord(TbCargoRecord tbCargoRecord){
        BaseResult baseResult = BaseResult.success();
        //入库非负验证
        if (tbCargoRecord.getEntryQuantity()!=null && tbCargoRecord.getEntryQuantity()<0){
            baseResult = BaseResult.fail("入库数不能为负数，请重新输入");
        }
        //出库非负验证
        if (tbCargoRecord.getDeliveryQuantity()!=null && tbCargoRecord.getDeliveryQuantity()<0){
            baseResult = BaseResult.fail("出库数不能为负数，请重新输入");
        }
        //验证货物名称和货物编号是否为一个货物
        if (!tbCargoRecord.getName().equals(tbCargoDao.getNameByNumber(tbCargoRecord.getNumber()))){
            baseResult = BaseResult.fail("货物不存在，请重新输入");
        }
        return baseResult;
    }
}
