package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.domain.TbCargoRecord;
import com.zk.warehouse.information.management.web.admin.abstracts.AbstractBaseController;
import com.zk.warehouse.information.management.web.admin.service.TbCargoRecordService;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
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

@Controller
@RequestMapping(value = "cargo/record")
public class CargoRecordController extends AbstractBaseController<TbCargoRecord,TbCargoRecordService> {
    @Autowired
    private TbWarehouseService tbWarehouseService;

    @ModelAttribute
    public TbCargoRecord getTbCargoRecordById(Long id){
        TbCargoRecord tbCargoRecord =null;
        if (id != null){
            tbCargoRecord = service.getById(id);
        }
        else {
            tbCargoRecord = new TbCargoRecord();
        }
        return tbCargoRecord;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbCargoRecord> tbCargoRecords = service.selectAll();
        model.addAttribute("tbCargoRecords",tbCargoRecords);
        return "cargo_record_list";
    }

    @RequestMapping(value = "entry",method = RequestMethod.GET)
    public String entry(Model model,TbCargoRecord tbCargoRecord){
        //查找已有仓库
        List<String> name = tbWarehouseService.getName();
        model.addAttribute("name",name);
        return "cargo_record_entry";
    }

    @RequestMapping(value = "delivery",method = RequestMethod.GET)
    public String delivery(Model model,TbCargoRecord tbCargoRecord){
        //查找已有仓库
        List<String> name = tbWarehouseService.getName();
        model.addAttribute("name",name);
        return "cargo_record_delivery";
    }

    @RequestMapping(value = "entrySave",method = RequestMethod.POST)
    public String entrySave(TbCargoRecord tbCargoRecord, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbCargoRecord);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/cargo/record/list";
        }
        //保存失败
        else {
            List<String> name = tbWarehouseService.getName();
            model.addAttribute("name",name);
            model.addAttribute("baseResult",baseResult);
            return "cargo_record_entry";
        }
    }

    @RequestMapping(value = "deliverySave",method = RequestMethod.POST)
    public String deliverySave(TbCargoRecord tbCargoRecord, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbCargoRecord);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/cargo/record/list";
        }
        //保存失败
        else {
            List<String> name = tbWarehouseService.getName();
            model.addAttribute("name",name);
            model.addAttribute("baseResult",baseResult);
            return "cargo_record_delivery";
        }
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbCargoRecord tbCargoRecord){
        return "cargo_record_detail";
    }
}
