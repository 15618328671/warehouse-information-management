package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.constant.ConstantUtils;
import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.utils.CookieUtils;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.domain.TbUser;
import com.zk.warehouse.information.management.web.admin.service.TbAdministratorService;
import com.zk.warehouse.information.management.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zk
 * @date 2020/1/15-15:22
 */
@Controller
public class LoginController {
    @Autowired
    private TbAdministratorService tbAdministratorService;

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest) {
        //是否有本地COOKIE记录
        String information = CookieUtils.getCookieValue(httpServletRequest, ConstantUtils.COOKIE_NAME_INFO);
        if (StringUtils.isNotBlank(information)) {
            String[] informationInfoArray = information.split(":");
            String username = informationInfoArray[0];
            String password = informationInfoArray[1];
            httpServletRequest.setAttribute("username", username);
            httpServletRequest.setAttribute("password", password);
            httpServletRequest.setAttribute("isRemember", true);
        }
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String username, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        TbAdministrator tbAdministrator = tbAdministratorService.login(username, password);
        TbUser tbUser = tbUserService.login(username, password);
        //是否选择记住我
        boolean isRemember = httpServletRequest.getParameter("isRemember") != null;
        //登陆失败
        if (tbAdministrator == null && tbUser == null) {
            httpServletRequest.setAttribute("message", "用户名或密码错误请重新输入");
            return login(httpServletRequest);
        }
        //登陆成功
        else {
            TbAdministrator administrator = (TbAdministrator) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_ADMINISTRATOR);
            TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            //已有管理员登陆
            if (administrator != null && tbAdministrator != null) {
                //返回管理员界面
                httpServletRequest.setAttribute("message", "已有管理员登录，请退出后重试");
                return login(httpServletRequest);
            }
            //已有用户存在
            if (user != null && tbUser != null) {
                //返回用户界面
                httpServletRequest.setAttribute("message", "已有用户登录，请退出后重试");
                return login(httpServletRequest);
            } else {
                //记住我，存放7天
                if (isRemember) {
                    CookieUtils.setCookie(httpServletRequest, httpServletResponse, ConstantUtils.COOKIE_NAME_INFO, String.format("%s:%s", username, password), 7 * 24 * 60 * 60);
                }
                //没有记住我
                else {
                    CookieUtils.deleteCookie(httpServletRequest, httpServletResponse, ConstantUtils.COOKIE_NAME_INFO);
                }
                //管理员登录
                if (tbAdministrator != null) {
                    //将登陆信息存入会话
                    httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_ADMINISTRATOR, tbAdministrator);
                    return "redirect:/main";
                }
                //用户登录
                else {
                    //将登陆信息存入会话
                    httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
                    return "redirect:/user_main";
                }
            }
        }
    }

}
