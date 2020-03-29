package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.domain.TbCargoRecord;
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
public class CargoRecordController {
    @Autowired
    private TbCargoRecordService tbCargoRecordService;

    @Autowired
    private TbWarehouseService tbWarehouseService;

    @ModelAttribute
    public TbCargoRecord getTbCargoRecordById(Long id){
        TbCargoRecord tbCargoRecord =null;
        if (id != null){
            tbCargoRecord = tbCargoRecordService.getById(id);
        }
        else {
            tbCargoRecord = new TbCargoRecord();
        }
        return tbCargoRecord;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbCargoRecord> tbCargoRecords = tbCargoRecordService.selectAll();
        model.addAttribute("tbCargoRecords",tbCargoRecords);
        return "cargo_record_list";
    }

    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<TbCargoRecord> page(HttpServletRequest httpServletRequest,TbCargoRecord tbCargoRecord){
        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装DataTables服务器返回结果
        PageInfo<TbCargoRecord> pageInfo = tbCargoRecordService.page(start,length,draw,tbCargoRecord);

        return pageInfo;
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

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbCargoRecord tbCargoRecord, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbCargoRecordService.save(tbCargoRecord);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/cargo/record/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "cargo_list";
        }
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbCargoRecord tbCargoRecord){
        return "cargo_record_detail";
    }
}
