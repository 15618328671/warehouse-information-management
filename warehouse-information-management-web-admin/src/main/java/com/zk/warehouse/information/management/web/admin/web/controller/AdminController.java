package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.web.admin.service.TbAdministratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zk
 * @date 2020/1/27-15:12
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @Autowired
    private TbAdministratorService tbAdministratorService;

    @ModelAttribute
    public TbAdministrator getTbAdministratorId(Long id){
        TbAdministrator tbAdministrator = null;

        //id不为空时获取用户信息
        if (id != null){
            tbAdministrator = tbAdministratorService.getById(id);
        }
        else {
            tbAdministrator = new TbAdministrator();
        }
        return tbAdministrator;
    }

    /**
     * 跳转到管理员列表
     * @param model
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbAdministrator> tbAdministrators = tbAdministratorService.selectAll();
        model.addAttribute("tbAdministrators",tbAdministrators);
        return "admin_list";
    }

    /**
     * 跳转到管理员表单
     * @return
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "admin_form";
    }

    /**
     * 保存用户信息
     * @param tbAdministrator
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbAdministrator tbAdministrator,Model model,RedirectAttributes redirectAttributes,@ModelAttribute("baseResult") BaseResult baseResult){
        baseResult = tbAdministratorService.save(tbAdministrator);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/admin/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "admin_form";
        }
    }

    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            tbAdministratorService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除成功");
        }
        else {
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbAdministrator> page(HttpServletRequest httpServletRequest,TbAdministrator tbAdministrator){
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装DataTables服务器返回结果
        PageInfo<TbAdministrator> pageInfo = tbAdministratorService.page(start, length, draw,tbAdministrator);

        return pageInfo;
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbAdministrator tbAdministrator){
        return "admin_detail";
    }
}