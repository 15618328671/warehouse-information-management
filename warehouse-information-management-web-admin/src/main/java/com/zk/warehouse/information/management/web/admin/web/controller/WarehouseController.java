package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.domain.TbWarehouse;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 仓库管理
 */
@Controller
@RequestMapping(value = "warehouse")
public class WarehouseController {
    @Autowired
    private TbWarehouseService tbWarehouseService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbWarehouse> tbWarehouses = tbWarehouseService.selectAll();
        model.addAttribute("tbWarehouses",tbWarehouses);
        return "warehouse_list";
    }
}
