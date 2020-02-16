package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.domain.TbContentCategory;
import com.zk.warehouse.information.management.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 * @author zk
 * @date 2020/2/12-14:30
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        //未排序源数据
        List<TbContentCategory> sourceList = tbContentCategoryService.selectAll();
        //排序后数据
        List<TbContentCategory> targetList = new ArrayList<>();
        sortList(sourceList,targetList,0L);

        model.addAttribute("tbContentCategories",targetList);
        return "content_category_list";
    }

    /**
     * 分类排序
     * @param sourceList    数据源集合
     * @param targetList    目标数据集合
     * @param parentId      父节点Id
     */
    public void sortList(List<TbContentCategory> sourceList,List<TbContentCategory> targetList,Long parentId){
        for (TbContentCategory tbContentCategory : sourceList) {
            if (tbContentCategory.getId().equals(parentId)){
                targetList.add(tbContentCategory);

                //判断是否含有子节点
                if (tbContentCategory.isParent()){
                    for (TbContentCategory contentCategory : sourceList) {
                        if (contentCategory.getParentId().equals(tbContentCategory.getId())){
                            sortList(sourceList,targetList,parentId);
                            break;
                        }
                    }
                }
            }
        }
    }
}
