package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.web.admin.abstracts.AbstractBaseController;
import com.zk.warehouse.information.management.web.admin.service.TbCargoService;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author zk
 * @date 2020/2/29-14:38
 */
@Controller
@RequestMapping(value = "cargo")
public class CargoController extends AbstractBaseController<TbCargo,TbCargoService> {
    @Autowired
    private TbWarehouseService tbWarehouseService;

    @ModelAttribute
    public TbCargo getTbCargoId(Long id){
        TbCargo tbCargo = null;
        if (id != null){
            tbCargo = service.getById(id);
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
        List<TbCargo> tbCargos = service.selectAll();
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
        BaseResult baseResult = service.save(tbCargo);
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
            service.deleteMulti(idArray);
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

    @RequestMapping(value = "export",method = RequestMethod.GET)
    public String export(Model model){
        List<TbCargo> tbCargos = service.selectAll();
        BaseResult baseResult = BaseResult.success("导出成功,表格目录为D:\\warehouse\\download");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //创建工作簿
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            //创建工作表
            XSSFSheet sheet = xssfWorkbook.createSheet("货物信息");
            //创建行
            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("货物编号");
            row.createCell(1).setCellValue("货物名称");
            row.createCell(2).setCellValue("货物库存");
            row.createCell(3).setCellValue("所属仓库");
            row.createCell(4).setCellValue("最新入库数量");
            row.createCell(5).setCellValue("最新入库时间");
            row.createCell(6).setCellValue("最新出库数量");
            row.createCell(7).setCellValue("最新出库时间");
            row.createCell(8).setCellValue("创建时间");
            row.createCell(9).setCellValue("更新时间");
            for (int i=0;i<tbCargos.size();i++){
                //自适应宽度
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i)*17/10);
                XSSFRow row1 = sheet.createRow(i + 1);
                row1.createCell(0).setCellValue(tbCargos.get(i).getNumber());
                row1.createCell(1).setCellValue(tbCargos.get(i).getName());
                row1.createCell(2).setCellValue(tbCargos.get(i).getInventory());
                row1.createCell(3).setCellValue(tbCargos.get(i).getParentId());
                if (tbCargos.get(i).getEntryQuantity()==null){
                    row1.createCell(4).setCellValue("");
                    row1.createCell(5).setCellValue("");
                }else {
                    row1.createCell(4).setCellValue(tbCargos.get(i).getEntryQuantity());
                    row1.createCell(5).setCellValue(simpleDateFormat.format(tbCargos.get(i).getEntryTime()));
                }
                if (tbCargos.get(i).getDeliveryQuantity()==null){
                    row1.createCell(6).setCellValue("");
                    row1.createCell(7).setCellValue("");
                }else {
                    row1.createCell(6).setCellValue(tbCargos.get(i).getDeliveryQuantity());
                    row1.createCell(7).setCellValue(simpleDateFormat.format(tbCargos.get(i).getDeliveryTime()));
                }
                row1.createCell(8).setCellValue(simpleDateFormat.format(tbCargos.get(i).getCreated()));
                row1.createCell(9).setCellValue(simpleDateFormat.format(tbCargos.get(i).getUpdated()));
            }

            //判断文件夹是否存在
            File file = new File("D:\\warehouse\\download");
            if (!file.exists()){
                file.mkdirs();
            }
            //导出数据
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\warehouse\\download\\货物信息表.xlsx");
            xssfWorkbook.write(fileOutputStream);

            //释放资源
            fileOutputStream.flush();
            fileOutputStream.close();
            xssfWorkbook.close();

            model.addAttribute("baseResult",baseResult);
            return "cargo_list";
        } catch (IOException e) {
            baseResult = BaseResult.fail("导出失败");
            model.addAttribute("baseResult",baseResult);
            return "cargo_list";
        }
    }
}
