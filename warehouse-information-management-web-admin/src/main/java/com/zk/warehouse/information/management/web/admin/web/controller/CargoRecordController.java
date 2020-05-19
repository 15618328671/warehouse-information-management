package com.zk.warehouse.information.management.web.admin.web.controller;

import com.sun.deploy.net.HttpResponse;
import com.zk.warehouse.information.management.commons.constant.ConstantUtils;
import com.zk.warehouse.information.management.commons.dto.BaseResult;
import com.zk.warehouse.information.management.commons.dto.PageInfo;
import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.domain.TbCargoRecord;
import com.zk.warehouse.information.management.domain.TbUser;
import com.zk.warehouse.information.management.web.admin.abstracts.AbstractBaseController;
import com.zk.warehouse.information.management.web.admin.service.TbAdministratorService;
import com.zk.warehouse.information.management.web.admin.service.TbCargoRecordService;
import com.zk.warehouse.information.management.web.admin.service.TbUserService;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.poi.ss.formula.functions.T;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping(value = "cargo/record")
public class CargoRecordController extends AbstractBaseController<TbCargoRecord,TbCargoRecordService> {
    @Autowired
    private TbWarehouseService tbWarehouseService;

    @Autowired
    private TbAdministratorService tbAdministratorService;

    @Autowired
    private TbUserService tbUserService;

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

    /**
     * 个人货物流动列表
     * @return
     */
    @RequestMapping(value = "user_list",method = RequestMethod.GET)
    public String userCargoRecordList(Model model){
        List<TbCargoRecord> tbCargoRecords = service.selectAll();
        model.addAttribute("tbCargoRecords",tbCargoRecords);
        return "user_cargo_record_list";
    }

    /**
     * 管理员操作入库
     * @param model
     * @param tbCargoRecord
     * @return
     */
    @RequestMapping(value = "entry",method = RequestMethod.GET)
    public String entry(Model model,TbCargoRecord tbCargoRecord){
        //查找已有仓库
        List<String> name = tbWarehouseService.getName();
        model.addAttribute("name",name);
        return "cargo_record_entry";
    }

    /**
     * 用户操作入库
     * @param model
     * @param tbCargoRecord
     * @return
     */
    @RequestMapping(value = "user_entry",method = RequestMethod.GET)
    public String userEntry(Model model,TbCargoRecord tbCargoRecord){
        //查找已有仓库
        List<String> name = tbWarehouseService.getName();
        model.addAttribute("name",name);
        return "user_cargo_record_entry";
    }

    /**
     * 管理员操作出库
     * @param model
     * @param tbCargoRecord
     * @return
     */
    @RequestMapping(value = "delivery",method = RequestMethod.GET)
    public String delivery(Model model,TbCargoRecord tbCargoRecord){
        //查找已有仓库
        List<String> name = tbWarehouseService.getName();
        model.addAttribute("name",name);
        return "cargo_record_delivery";
    }

    /**
     * 用户操作出库
     * @param model
     * @param tbCargoRecord
     * @return
     */
    @RequestMapping(value = "user_delivery",method = RequestMethod.GET)
    public String userDelivery(Model model,TbCargoRecord tbCargoRecord){
        //查找已有仓库
        List<String> name = tbWarehouseService.getName();
        model.addAttribute("name",name);
        return "user_cargo_record_delivery";
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

    @RequestMapping(value = "user_entrySave",method = RequestMethod.POST)
    public String userEntrySave(TbCargoRecord tbCargoRecord, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbCargoRecord);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/cargo/record/user_list";
        }
        //保存失败
        else {
            List<String> name = tbWarehouseService.getName();
            model.addAttribute("name",name);
            model.addAttribute("baseResult",baseResult);
            return "user_cargo_record_entry";
        }
    }

    @RequestMapping(value = "user_deliverySave",method = RequestMethod.POST)
    public String userDeliverySave(TbCargoRecord tbCargoRecord, Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult = service.save(tbCargoRecord);
        //保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/cargo/record/user_list";
        }
        //保存失败
        else {
            List<String> name = tbWarehouseService.getName();
            model.addAttribute("name",name);
            model.addAttribute("baseResult",baseResult);
            return "user_cargo_record_delivery";
        }
    }

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbCargoRecord tbCargoRecord){
        return "cargo_record_detail";
    }

    @RequestMapping(value = "export",method = RequestMethod.GET)
    public String export(Model model){
        List<TbCargoRecord> tbCargoRecords = service.selectAll();
        BaseResult baseResult = BaseResult.success("导出成功,表格目录为D:\\warehouse\\download\\admin");
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
            row.createCell(10).setCellValue("操作者");
            for (int i=0;i<tbCargoRecords.size();i++){
                //自适应宽度
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i)*17/10);
                XSSFRow row1 = sheet.createRow(i + 1);
                row1.createCell(0).setCellValue(tbCargoRecords.get(i).getNumber());
                row1.createCell(1).setCellValue(tbCargoRecords.get(i).getName());
                row1.createCell(2).setCellValue(tbCargoRecords.get(i).getInventory());
                row1.createCell(3).setCellValue(tbCargoRecords.get(i).getParentId());
                if (tbCargoRecords.get(i).getEntryQuantity()==null){
                    row1.createCell(4).setCellValue("");
                    row1.createCell(5).setCellValue("");
                }else {
                    row1.createCell(4).setCellValue(tbCargoRecords.get(i).getEntryQuantity());
                    row1.createCell(5).setCellValue(simpleDateFormat.format(tbCargoRecords.get(i).getEntryTime()));
                }
                if (tbCargoRecords.get(i).getDeliveryQuantity()==null){
                    row1.createCell(6).setCellValue("");
                    row1.createCell(7).setCellValue("");
                }else {
                    row1.createCell(6).setCellValue(tbCargoRecords.get(i).getDeliveryQuantity());
                    row1.createCell(7).setCellValue(simpleDateFormat.format(tbCargoRecords.get(i).getDeliveryTime()));
                }
                row1.createCell(8).setCellValue(simpleDateFormat.format(tbCargoRecords.get(i).getCreated()));
                row1.createCell(9).setCellValue(simpleDateFormat.format(tbCargoRecords.get(i).getUpdated()));
                row1.createCell(10).setCellValue(tbCargoRecords.get(i).getHandlers());
            }

            //判断文件夹是否存在
            File file = new File("D:\\warehouse\\download\\admin");
            if (!file.exists()){
                file.mkdirs();
            }
            //导出数据
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\warehouse\\download\\admin\\货物流动信息表.xlsx");
            xssfWorkbook.write(fileOutputStream);

            //释放资源
            fileOutputStream.flush();
            fileOutputStream.close();
            xssfWorkbook.close();

            model.addAttribute("baseResult",baseResult);
            return "cargo_record_list";
        } catch (IOException e) {
            baseResult = BaseResult.fail("导出失败");
            model.addAttribute("baseResult",baseResult);
            return "cargo_record_list";
        }
    }

    @RequestMapping(value = "user_export",method = RequestMethod.GET)
    public String userExport(Model model,HttpServletRequest httpServletRequest){
        TbUser tbUser = (TbUser)httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        List<TbCargoRecord> tbCargoRecords = service.selectByHandlers(tbUser.getUsername());
        BaseResult baseResult = BaseResult.success("导出成功,表格目录为D:\\warehouse\\download\\user");
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
            row.createCell(10).setCellValue("操作者");
            for (int i=0;i<tbCargoRecords.size();i++){
                //自适应宽度
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i)*17/10);
                XSSFRow row1 = sheet.createRow(i + 1);
                row1.createCell(0).setCellValue(tbCargoRecords.get(i).getNumber());
                row1.createCell(1).setCellValue(tbCargoRecords.get(i).getName());
                row1.createCell(2).setCellValue(tbCargoRecords.get(i).getInventory());
                row1.createCell(3).setCellValue(tbCargoRecords.get(i).getParentId());
                if (tbCargoRecords.get(i).getEntryQuantity()==null){
                    row1.createCell(4).setCellValue("");
                    row1.createCell(5).setCellValue("");
                }else {
                    row1.createCell(4).setCellValue(tbCargoRecords.get(i).getEntryQuantity());
                    row1.createCell(5).setCellValue(simpleDateFormat.format(tbCargoRecords.get(i).getEntryTime()));
                }
                if (tbCargoRecords.get(i).getDeliveryQuantity()==null){
                    row1.createCell(6).setCellValue("");
                    row1.createCell(7).setCellValue("");
                }else {
                    row1.createCell(6).setCellValue(tbCargoRecords.get(i).getDeliveryQuantity());
                    row1.createCell(7).setCellValue(simpleDateFormat.format(tbCargoRecords.get(i).getDeliveryTime()));
                }
                row1.createCell(8).setCellValue(simpleDateFormat.format(tbCargoRecords.get(i).getCreated()));
                row1.createCell(9).setCellValue(simpleDateFormat.format(tbCargoRecords.get(i).getUpdated()));
                row1.createCell(10).setCellValue(tbCargoRecords.get(i).getHandlers());
            }

            //判断文件夹是否存在
            File file = new File("D:\\warehouse\\download\\user");
            if (!file.exists()){
                file.mkdirs();
            }
            //导出数据
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\warehouse\\download\\user\\货物流动信息表.xlsx");
            xssfWorkbook.write(fileOutputStream);

            //释放资源
            fileOutputStream.flush();
            fileOutputStream.close();
            xssfWorkbook.close();

            model.addAttribute("baseResult",baseResult);
            return "user_cargo_record_list";
        } catch (IOException e) {
            baseResult = BaseResult.fail("导出失败");
            model.addAttribute("baseResult",baseResult);
            return "user_cargo_record_list";
        }
    }

    /**
     * 用户货物流动分页查询
     * @param httpServletRequest
     * @param tbCargoRecord
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "user_page",method = RequestMethod.GET)
    public PageInfo<TbCargoRecord> userPage(HttpServletRequest httpServletRequest,TbCargoRecord tbCargoRecord){
        //获取当前用户
        TbUser tbUser = (TbUser)httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        tbCargoRecord.setHandlers(tbUser.getUsername());

        String strDraw = httpServletRequest.getParameter("draw");
        String strStart = httpServletRequest.getParameter("start");
        String strLength = httpServletRequest.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装DataTables服务器返回结果
        PageInfo<TbCargoRecord> pageInfo = service.page(start, length, draw,tbCargoRecord);

        return pageInfo;
    }

    @RequestMapping(value = "comment",method = RequestMethod.GET)
    public String comment(){
        return "comment";
    }

    @RequestMapping(value = "comment_save",method = RequestMethod.POST)
    public String commentSave(TbCargoRecord tbCargoRecord,Model model,RedirectAttributes redirectAttributes){
        //提交评论
        service.update(tbCargoRecord);
        TbAdministrator administrator = tbAdministratorService.getByUsername(tbCargoRecord.getHandlers());
        TbUser user = tbUserService.getByUsername(tbCargoRecord.getHandlers());
        BaseResult baseResult = null;
        String emailAddress = null;
        if (administrator != null){
            emailAddress = administrator.getEmail();
        }else if (user != null){
            emailAddress = user.getEmail();
        }
        //用户存在
        if (administrator !=null || user != null){
            if(emailAddress != null) {
                try {
                    //发送邮件
                    Email email = new SimpleEmail();
                    email.setHostName("smtp.qq.com");
                    email.setSmtpPort(465);
                    email.setAuthenticator(new DefaultAuthenticator("1214159039@qq.com", "sxjqurypcskchahb"));
                    email.setSSLOnConnect(true);
                    email.setFrom("1214159039@qq.com");
                    email.setSubject("货物订单评论");
                    email.setMsg("管理员对你的订单" + tbCargoRecord.getId() + "给予评价，请及时查看");
                    email.addTo(emailAddress);
                    email.send();
                    //邮件发送成功
                    baseResult = BaseResult.success("评论成功，并发出邮件提示");
                    redirectAttributes.addFlashAttribute("baseResult", baseResult);
                    return "redirect:/cargo/record/list";
                } catch (EmailException e) {
                    baseResult = BaseResult.fail("邮箱发送失败");
                    model.addAttribute("baseResult", baseResult);
                    return "cargo_record_list";
                }
            }else {
                baseResult = BaseResult.success("评论成功");
                redirectAttributes.addFlashAttribute("baseResult",baseResult);
                return "redirect:/cargo/record/list";
            }
        }else {
            baseResult = BaseResult.success("评论成功");
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/cargo/record/list";
        }
    }
}
