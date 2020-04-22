package com.zk.warehouse.information.management.web.admin.service.impl;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.commons.utils.RegexUtils;
import com.zk.warehouse.information.management.commons.validator.BeanValidator;
import com.zk.warehouse.information.management.domain.TbUser;
import com.zk.warehouse.information.management.web.admin.dao.TbUserDao;
import com.zk.warehouse.information.management.web.admin.service.TbUserService;
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
 * @date 2020/4/13-15:46
 */
@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser entity) {
        String validator = BeanValidator.validator(entity);
        //validator验证不通过
        if (validator != null){
            return BaseResult.fail(validator);
        }
        //Validation验证通过
        else {
            BaseResult baseResult = checkUser(entity);
            //通过验证
            if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
                entity.setUpdated(new Date());
                //新增用户
                if (entity.getId() == null){
                    //md5密码加密处理
                    entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()));
                    entity.setCreated(new Date());
                    entity.setLevel(false);
                    tbUserDao.insert(entity);
                }
                //编辑用户
                else {
                    //md5密码加密处理
                    entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()));
                    tbUserDao.update(entity);
                }
                return BaseResult.success("保存用户信息成功");
            }
            //验证失败
            else {
                return baseResult;
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void insert(TbUser entity) {
        tbUserDao.insert(entity);
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start, int length, int draw, TbUser entity) {
        int count = tbUserDao.count(entity);
        Map<String,Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbUser",entity);

        PageInfo<TbUser> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbUserDao.page(params));

        return pageInfo;
    }

    @Override
    public int count(TbUser entity) {
        return tbUserDao.count(entity);
    }

    @Override
    public int countUsername(TbUser tbUser) {
        return tbUserDao.haveUsername(tbUser);
    }

    @Override
    public TbUser login(String username, String password) {
        TbUser tbUser = tbUserDao.getByUsername(username);
        if (tbUser != null) {
            //md5加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //加密后的密码与存放密码相同，登陆成功
            if (md5Password.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return tbUser;
    }

    /**
     * 用户信息验证
     * @param tbUser
     * @return
     */
    private BaseResult checkUser(TbUser tbUser){
        BaseResult baseResult = BaseResult.success();
        if (!RegexUtils.checkPhone(tbUser.getPhone())){
            baseResult = BaseResult.fail("手机格式不正确，请重新输入");
        } else if (!RegexUtils.checkEmail(tbUser.getEmail())){
            baseResult = BaseResult.fail("电子邮箱格式不正确，请重新输入");
        }else if (tbUserDao.haveUsername(tbUser)>0){
            baseResult = BaseResult.fail("用户名已存在，请重新输入");
        }else if (tbUserDao.havePhone(tbUser)>0){
            baseResult = BaseResult.fail("电话已注册，请重新输入");
        }else if(tbUserDao.haveEmail(tbUser)>0){
            baseResult = BaseResult.fail("电子邮箱已注册，请重新输入");
        }
        return baseResult;
    }
}
