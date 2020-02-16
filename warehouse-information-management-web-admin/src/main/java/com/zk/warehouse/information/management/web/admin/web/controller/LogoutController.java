package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.constant.ConstantUtils;
import com.zk.warehouse.information.management.commons.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zk
 * @date 2020/1/20-11:14
 */
@Controller
public class LogoutController {
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CookieUtils.deleteCookie(httpServletRequest,httpServletResponse, ConstantUtils.COOKIE_NAME_ADMINISTRATOR_INFO);
        httpServletRequest.getSession().invalidate();
        return "login";
    }
}
