package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.commons.validator.BeanValidator;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.web.admin.dao.TbAdministratorDao;
import com.zk.warehouse.information.management.web.admin.service.TbAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2020/1/18-14:46
 */
@Service
@Transactional(readOnly = true)
public class TbAdministratorServiceImpl implements TbAdministratorService {
    @Autowired
    private TbAdministratorDao tbAdministratorDao;

    @Override
    public List<TbAdministrator> selectAll() {
        return tbAdministratorDao.selectAll();
    }

    @Override
    public TbAdministrator login(String username, String password) {
        TbAdministrator tbAdministrator = tbAdministratorDao.getByUsername(username);
        if (tbAdministrator != null) {
            //md5加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //加密后的密码与存放密码相同，登陆成功
            if (md5Password.equals(tbAdministrator.getPassword())) {
                return tbAdministrator;
            }
        }
        return null;
    }

    @Override
    public TbAdministrator getByUsername(String username) {
        return tbAdministratorDao.getByUsername(username);
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbAdministrator tbAdministrator) {
        String validator = BeanValidator.validator(tbAdministrator);
        //Validation验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //Validation验证通过
        else {
            BaseResult baseResult = checkAdministrator(tbAdministrator);
            if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {

                tbAdministrator.setUpdated(new Date());
                //新增用户
                if (tbAdministrator.getId() == null) {

                    //md5密码加密处理
                    tbAdministrator.setPassword(DigestUtils.md5DigestAsHex(tbAdministrator.getPassword().getBytes()));
                    tbAdministrator.setCreated(new Date());
                    tbAdministrator.setLevel(true);
                    tbAdministratorDao.insert(tbAdministrator);
                }
                //编辑用户
                else {
                    //md5密码加密处理
                    tbAdministrator.setPassword(DigestUtils.md5DigestAsHex(tbAdministrator.getPassword().getBytes()));
                    tbAdministratorDao.update(tbAdministrator);
                }
                return BaseResult.success("保存用户信息成功");
            } else {
                return baseResult;
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void insert(TbAdministrator tbAdministrator) {
        tbAdministratorDao.insert(tbAdministrator);
    }

    @Override
    public TbAdministrator getById(Long id) {
        return tbAdministratorDao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids) {
        tbAdministratorDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbAdministrator> page(int start, int length, int draw, TbAdministrator tbAdministrator) {
        int count = tbAdministratorDao.count(tbAdministrator);
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbAdministrator", tbAdministrator);

        PageInfo<TbAdministrator> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbAdministratorDao.page(params));

        return pageInfo;
    }

    @Override
    public int count(TbAdministrator tbAdministrator) {
        return tbAdministratorDao.count(tbAdministrator);
    }

    /**
     * 重复性验证
     * @param tbAdministrator
     * @return
     */
    private BaseResult checkAdministrator(TbAdministrator tbAdministrator) {
        BaseResult baseResult = BaseResult.success();
        if (tbAdministratorDao.haveUsername(tbAdministrator) > 0) {
            baseResult = BaseResult.fail("用户名已存在,请重新输入");
        } else if (tbAdministratorDao.havePhone(tbAdministrator) > 0) {
            baseResult = BaseResult.fail("手机号已注册,请重新输入");
        } else if (tbAdministratorDao.haveEmail(tbAdministrator) > 0) {
            baseResult = BaseResult.fail("邮箱已注册,请重新输入");
        }
        return baseResult;
    }
}
