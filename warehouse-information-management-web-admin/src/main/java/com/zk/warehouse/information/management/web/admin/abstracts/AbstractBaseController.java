package com.zk.warehouse.information.management.web.admin.abstracts;

import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.commons.persistence.BaseEntity;
import com.zk.warehouse.information.management.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zk
 * @date 2020/4/15-18:32
 */
public abstract class AbstractBaseController<T extends BaseEntity,S extends BaseService<T>> {
    @Autowired
    protected S service;

    /**
     * 分页查询
     * @param httpServletRequest
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<T> page(HttpServletRequest httpServletRequest, T entity){
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装DataTables服务器返回结果
        PageInfo<T> pageInfo = service.page(start, length, draw,entity);

        return pageInfo;
    }
}
