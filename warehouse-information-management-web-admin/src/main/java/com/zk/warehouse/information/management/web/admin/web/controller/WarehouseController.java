package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.domain.TbWarehouse;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
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
        List<TbWarehouse> sourceList = tbWarehouseService.selectAll();
        List<TbWarehouse> targetList = new ArrayList<>();

        //排序
        sortList(sourceList,targetList,"0");

        model.addAttribute("tbWarehouses",targetList);
        return "warehouse_list";
    }

    /**
     * 排序
     * @para  sortList   数据源集合
     * @param targetList 排序后集合
     * @param parentId   父类目Id
     */
    private void sortList(List<TbWarehouse> sourceList,List<TbWarehouse> targetList,String parentId){
        for (TbWarehouse tbWarehouse : sourceList) {
            if (tbWarehouse.getParentId().equals(parentId)){
                targetList.add(tbWarehouse);

                //判断是否有子节点
                if (tbWarehouse.getParent()){
                    for (TbWarehouse warehouse : sourceList) {
                        if (warehouse.getParentId().equals(tbWarehouse.getNumber())){
                            sortList(sourceList,targetList,warehouse.getParentId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
