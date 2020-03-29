package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.commons.validator.BeanValidator;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.web.admin.dao.TbCargoDao;
import com.zk.warehouse.information.management.web.admin.dao.TbWarehouseDao;
import com.zk.warehouse.information.management.web.admin.service.TbCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2020/2/29-14:35
 */
@Service
@Transactional(readOnly = true)
public class TbCargoServiceImpl implements TbCargoService {
    @Autowired
    private TbCargoDao tbCargoDao;

    @Autowired
    private TbWarehouseDao tbWarehouseDao;

    @Override
    public List<TbCargo> selectAll() {
        return tbCargoDao.selectAll();
    }

    @Override
    public TbCargo getById(Long id) {
        return tbCargoDao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbCargo tbCargo) {
        String validator = BeanValidator.validator(tbCargo);
        //Validation验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //Validation验证通过
        else {
            BaseResult baseResult = checkCargo(tbCargo);
            if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
                tbCargo.setUpdated(new Date());
                //新增货物
                if (tbCargo.getId() == null) {
                    //通过重复判断
                    tbCargo.setIsParent(false);
                    tbCargo.setCreated(new Date());
                    tbCargo.setInventory(0D);
                    tbCargoDao.insert(tbCargo);
                }
                //编辑货物
                else {
                    tbCargoDao.update(tbCargo);
                }
                return BaseResult.success("保存货物信息成功");
            }
            else {
                return baseResult;
            }
        }
    }

    @Override
    public void insert(TbCargo entity) {

    }

    @Override
    public PageInfo<TbCargo> page(int start, int length, int draw, TbCargo tbCargo) {
        int count = tbCargoDao.count(tbCargo);
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbCargo", tbCargo);

        PageInfo<TbCargo> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbCargoDao.page(params));
        return pageInfo;
    }

    @Override
    public int count(TbCargo entity) {
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids) {
        tbCargoDao.deleteMulti(ids);
    }


    /**
     * 货物重复性验证
     *
     * @param tbCargo
     * @return
     */
    private BaseResult checkCargo(TbCargo tbCargo) {
        BaseResult baseResult = BaseResult.success();
        if (tbCargo.getParentId().equals("NONE")) {
            baseResult = BaseResult.fail("请选择所属仓库");
        }
        //重复判断
        else if (tbCargoDao.countName(tbCargo) > 0) {
            baseResult = BaseResult.fail("货物已经存在，请重新输入");
        }
        else if(tbWarehouseDao.countByName(tbCargo.getName())>0){
            baseResult = BaseResult.fail("货物名不能与仓库名相同，请重新输入");
        }
        else if (tbCargoDao.countNumber(tbCargo) > 0) {
            baseResult = BaseResult.fail("货物编号已经存在，请重新输入");
        }
        return baseResult;
    }
}
