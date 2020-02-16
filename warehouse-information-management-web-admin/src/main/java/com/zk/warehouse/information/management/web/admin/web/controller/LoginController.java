package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.constant.ConstantUtils;
import com.zk.warehouse.information.management.commons.utils.CookieUtils;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.web.admin.service.TbAdministratorService;
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

    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest) {
        TbAdministrator tbadministrator = (TbAdministrator) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_ADMINISTRATOR);
        //已经登陆
        if (tbadministrator != null) {
            return "redirect:/main";
        }else {
            //是否有本地COOKIE记录
            String administratorInfo = CookieUtils.getCookieValue(httpServletRequest,ConstantUtils.COOKIE_NAME_ADMINISTRATOR_INFO);
            if (StringUtils.isNotBlank(administratorInfo)){
                String[] administratorInfoArray = administratorInfo.split(":");
                String username = administratorInfoArray[0];
                String password = administratorInfoArray[1];
                httpServletRequest.setAttribute("username",username);
                httpServletRequest.setAttribute("password",password);
                httpServletRequest.setAttribute("isRemember",true);
            }
        }
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String username, @RequestParam(required = true) String password,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
        TbAdministrator tbadministrator = tbAdministratorService.login(username, password);
        Boolean isRemember = httpServletRequest.getParameter("isRemember") != null;
        //登陆失败
        if (tbadministrator == null) {
            httpServletRequest.setAttribute("message","用户名或密码错误请重新输入");
            return login(httpServletRequest);
        }
        //登陆成功
        else {
            //记住我，存放7天
            if (isRemember){
                CookieUtils.setCookie(httpServletRequest,httpServletResponse,ConstantUtils.COOKIE_NAME_ADMINISTRATOR_INFO,String.format("%s:%s",username,password),7*24*60*60);
            }
            //没有记住我
            else {
                CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,ConstantUtils.COOKIE_NAME_ADMINISTRATOR_INFO);
            }
            //将登陆信息存入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_ADMINISTRATOR, tbadministrator);
            return "redirect:/main";
        }
    }

}