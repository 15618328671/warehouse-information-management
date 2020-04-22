package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.web.admin.abstracts.AbstractBaseController;
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
public class AdminController extends AbstractBaseController<TbAdministrator,TbAdministratorService> {
    @ModelAttribute
    public TbAdministrator getTbAdministratorId(Long id){
        TbAdministrator tbAdministrator = null;

        //id不为空时获取用户信息
        if (id != null){
            tbAdministrator = service.getById(id);
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
        List<TbAdministrator> tbAdministrators = service.selectAll();
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
     * 跳转到修改管理员个人信息页
     * @return
     */
    @RequestMapping(value = "information/form",method = RequestMethod.GET)
    public String informationForm(){
        return "admin_information_form";
    }

    /**
     * 保存用户信息
     * @param tbAdministrator
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbAdministrator tbAdministrator, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbAdministrator);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/admin/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "admin_form";
        }
    }

    /**
     * 保存用户个人信息
     * @param tbAdministrator
     * @return
     */
    @RequestMapping(value = "information/save",method = RequestMethod.POST)
    public String informationSave(TbAdministrator tbAdministrator, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbAdministrator);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/admin/information";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "admin_information_form";
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
        BaseResult baseResult;
        if (StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除成功");
        }
        else {
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }



    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbAdministrator tbAdministrator){
        return "admin_detail";
    }

    @RequestMapping(value = "information",method = RequestMethod.GET)
    public String information(){
        return "admin_information";
    }
}
