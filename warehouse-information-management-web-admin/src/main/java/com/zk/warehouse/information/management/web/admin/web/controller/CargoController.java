package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.web.admin.service.TbCargoService;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.ls.LSException;

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
            tbCargo = tbCargoService.getById();
        }
        else {
            tbCargo = new TbCargo();
        }
        return tbCargo;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbCargo> tbCargos = tbCargoService.selectAll();
        model.addAttribute("tbCargos",tbCargos);
        return "cargo_list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(Model model){
        //查找已有仓库
        List<String> name = tbWarehouseService.getName();
        model.addAttribute("name",name);
        return "cargo_form";
    }
}
