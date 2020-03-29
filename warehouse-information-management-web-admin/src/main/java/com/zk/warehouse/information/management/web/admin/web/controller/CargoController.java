package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.web.admin.service.TbCargoService;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
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
 * @date 2020/2/29-14:38
 */
@Controller
@RequestMapping(value = "cargo")
public class CargoController {
    @Autowired
    private TbCargoService tbCargoService;

    @Autowired
    private TbWarehouseService tbWarehouseService;

    @ModelAttribute
    public TbCargo getTbCargoId(Long id){
        TbCargo tbCargo = null;
        if (id != null){
            tbCargo = tbCargoService.getById(id);
        }
        else {
            tbCargo = new TbCargo();
        }
        return tbCargo;
    }

    /**
     * 货物列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbCargo> tbCargos = tbCargoService.selectAll();
        model.addAttribute("tbCargos",tbCargos);
        return "cargo_list";
    }

    /**
     * 货物表单页
     * @param model
     * @param tbCargo
     * @return
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(Model model,TbCargo tbCargo){
        //查找已有仓库
        List<String> name = tbWarehouseService.getName();
        model.addAttribute("name",name);
        return "cargo_form";
    }

    /**
     * 保存功能
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbCargo tbCargo, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbCargoService.save(tbCargo);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/cargo/list";
        }
        //保存失败
        else {
            //查找已有仓库
            List<String> name = tbWarehouseService.getName();
            model.addAttribute("name",name);
            model.addAttribute("baseResult",baseResult);
            return "cargo_form";
        }
    }

    /**
     * 分页功能
     * @param httpServletRequest
     * @param tbCargo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbCargo> page(HttpServletRequest httpServletRequest,TbCargo tbCargo){
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装DataTables服务器返回结果
        PageInfo<TbCargo> pageInfo = tbCargoService.page(start,length,draw,tbCargo);

        return pageInfo;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            tbCargoService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除成功");
        }
        else {
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbCargo tbCargo){
        return "cargo_detail";
    }
}
