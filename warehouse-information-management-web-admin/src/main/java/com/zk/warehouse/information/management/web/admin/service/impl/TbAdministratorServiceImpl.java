package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.commons.utils.RegexUtils;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.web.admin.dao.TbAdministratorDao;
import com.zk.warehouse.information.management.web.admin.service.TbAdministratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public BaseResult save(TbAdministrator tbAdministrator) {
        BaseResult baseResult = checkTbAdministrator(tbAdministrator);
        //通过验证
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            tbAdministrator.setUpdated(new Date());

            //新增用户
            if (tbAdministrator.getId() == null) {
                //md5密码加密处理
                tbAdministrator.setPassword(DigestUtils.md5DigestAsHex(tbAdministrator.getUsername().getBytes()));
                tbAdministrator.setCreated(new Date());
                tbAdministratorDao.insert(tbAdministrator);
            }
            //编辑用户
            else {
                //md5密码加密处理
                tbAdministrator.setPassword(DigestUtils.md5DigestAsHex(tbAdministrator.getUsername().getBytes()));
                tbAdministratorDao.update(tbAdministrator);
            }

            baseResult.setMessage("保存管理员信息成功");
        }

        return baseResult;
    }

    @Override
    public void insert(TbAdministrator tbAdministrator) {
        tbAdministratorDao.insert(tbAdministrator);
    }

    @Override
    public TbAdministrator getById(Long id) {
        return tbAdministratorDao.getById(id);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbAdministratorDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbAdministrator> page(int start, int length,int draw,TbAdministrator tbAdministrator) {
        int count = tbAdministratorDao.count(tbAdministrator);
        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("tbAdministrator",tbAdministrator);

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
     * 用户信息的有效性验证
     *
     * @param tbAdministrator
     */
    private BaseResult checkTbAdministrator(TbAdministrator tbAdministrator) {
        BaseResult baseResult = BaseResult.success();
        //非空验证
        if (StringUtils.isBlank(tbAdministrator.getUsername())){
            baseResult = BaseResult.fail("用户名不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbAdministrator.getPassword())){
            baseResult = BaseResult.fail("密码不能为空，请重新输入");
        }
        else if (StringUtils.isBlank(tbAdministrator.getPhone())){
            baseResult = BaseResult.fail("手机号不能为空，请重新输入");
        }
        else if (RegexUtils.checkPhone(tbAdministrator.getPhone())){
            baseResult = BaseResult.fail("手机号格式不正确，请重新输入");
        }
        else if(StringUtils.isBlank(tbAdministrator.getEmail())){
            baseResult = BaseResult.fail("邮箱地址不能为空，请重新输入");
        }
        else if (RegexUtils.checkEmail(tbAdministrator.getEmail())){
            baseResult = BaseResult.fail("邮箱地址格式不正确，请重新输入");
        }
        return baseResult;
    }
}
