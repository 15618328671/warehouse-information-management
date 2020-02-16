package com.zk.warehouse.information.management.web.admin.web.interceptor;

import com.zk.warehouse.information.management.commons.constant.ConstantUtils;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zk
 * @date 2020/1/15-15:40
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbAdministrator tbAdministrator = (TbAdministrator) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_ADMINISTRATOR);
        //没有登陆
        if (tbAdministrator == null){
            httpServletResponse.sendRedirect("/login");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
