package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.domain.TbWarehouse;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @ModelAttribute
    public TbWarehouse getTbWarehouseById(Long id){
        TbWarehouse tbWarehouse = null;
        if (id != null){
            tbWarehouse = tbWarehouseService.getById(id);
        }
        else {
            tbWarehouse = new TbWarehouse();
        }
        return tbWarehouse;
    }

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
                if (tbWarehouse.getIsParent()){
                    for (TbWarehouse warehouse : sourceList) {
                        if (warehouse.getParentId().equals(tbWarehouse.getName())){
                            sortList(sourceList,targetList,warehouse.getParentId());
                            break;
                        }
                    }
                }
            }
        }
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "warehouse_form";
    }

    @RequestMapping(value = "save",method = RequestMethod.GET)
    public String save(TbWarehouse tbWarehouse, RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbWarehouseService.save(tbWarehouse);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/warehouse/list";
        }
        //保存失败
        else {
            return "warehouse_form";
        }
    }
}
