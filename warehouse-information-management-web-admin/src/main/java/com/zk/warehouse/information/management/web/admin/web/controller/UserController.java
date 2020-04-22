package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.domain.TbUser;
import com.zk.warehouse.information.management.web.admin.abstracts.AbstractBaseController;
import com.zk.warehouse.information.management.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author zk
 * @date 2020/4/19-21:48
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser, TbUserService> {
    @ModelAttribute
    public TbUser getUserById(Long id){
        TbUser tbUser = null;

        //id不为空时获取用户信息
        if (id != null){
            tbUser = service.getById(id);
        }
        else {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    /**
     * 跳转到用户列表
     * @param
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    /**
     * 跳转到用户表单
     * @return
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    /**
     * 跳转到用户个人信息修改页
     * @return
     */
    @RequestMapping(value = "/information/form",method = RequestMethod.GET)
    public String informationForm(){
        return "user_information_form";
    }

    /**
     * 保存用户信息
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbUser);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }

    /**
     * 保存用户个人信息
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "information/save",method = RequestMethod.POST)
    public String informationSave(TbUser tbUser, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbUser);

        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            model.addAttribute("baseResult", baseResult);
            return "user_information";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "user_information_form";
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
    public String detail(TbUser tbUser){
        return "user_detail";
    }

    @RequestMapping(value = "information",method = RequestMethod.GET)
    public String information(){
        return "user_information";
    }
}
