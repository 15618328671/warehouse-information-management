package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.commons.validator.BeanValidator;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.web.admin.dao.TbCargoDao;
import com.zk.warehouse.information.management.web.admin.service.TbCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2020/2/29-14:35
 */
@Service
public class TbCargoServiceImpl implements TbCargoService {
    @Autowired
    private TbCargoDao tbCargoDao;

    @Override
    public List<TbCargo> selectAll() {
        return tbCargoDao.selectAll();
    }

    @Override
    public TbCargo getById(Long id) {
        return tbCargoDao.getById(id);
    }

    @Override
    public BaseResult save(TbCargo tbCargo) {
        String validator = BeanValidator.validator(tbCargo);
        //Validation验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //Validation验证通过
        else {
            BaseResult baseResult = checkCargo(tbCargo);
            //通过重复判断
            if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
                tbCargo.setUpdated(new Date());
                //新增货物
                if (tbCargo.getId() == null) {
                    tbCargo.setIsParent(false);
                    tbCargo.setCreated(new Date());
                    tbCargoDao.insert(tbCargo);
                }
                //编辑货物
                else {
                    tbCargoDao.update(tbCargo);
                }
                baseResult.setMessage("保存货物信息成功");
            }
            return baseResult;
        }
    }

    @Override
    public void insert(TbCargo entity) {

    }

    @Override
    public PageInfo<TbCargo> page(int start, int length, int draw, TbCargo tbCargo) {
        int count = tbCargoDao.count(tbCargo);
        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbCargo",tbCargo);

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
    public void deleteMulti(String[] ids) {
        tbCargoDao.deleteMulti(ids);
    }


    /**
     * 货物重复性验证
     * @param tbCargo
     * @return
     */
    private BaseResult checkCargo(TbCargo tbCargo){
        BaseResult baseResult = BaseResult.success();
        if (tbCargo.getParentId().equals("NONE")){
            baseResult = BaseResult.fail("请选择所属仓库");
        }
        //重复判断
        else if (tbCargoDao.countName(tbCargo.getName())>0){
            baseResult = BaseResult.fail("货物已经存在，请重新输入");
        }
        else if (tbCargoDao.countNumber(tbCargo.getNumber())>0){
            baseResult = BaseResult.fail("货物编号已经存在，请重新输入");
        }
        return baseResult;
    }
}
