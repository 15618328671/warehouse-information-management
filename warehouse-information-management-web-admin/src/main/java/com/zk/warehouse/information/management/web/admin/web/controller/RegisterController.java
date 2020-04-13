package com.zk.warehouse.information.management.web.admin.web.controller;

import com.zk.warehouse.information.management.domain.TbUser;
import com.zk.warehouse.information.management.web.admin.service.TbAdministratorService;
import com.zk.warehouse.information.management.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author zk
 * @date 2020/4/13-14:55
 */
@Controller
public class RegisterController {
    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbAdministratorService tbAdministratorService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@RequestParam(required = true) String username, @RequestParam(required = true) String password, @RequestParam(required = true) String rePassword, Model model) {
        if (!password.equals(rePassword)) {
            model.addAttribute("message", "两次密码不相同，请重新输入");
            return register();
        }
        if (tbUserService.countUsername(username) > 0) {
            model.addAttribute("message", "用户名已存在，请重新输入");
            return register();
        }
        TbUser tbUser = new TbUser();
        tbUser.setUsername(username);
        tbUser.setPassword(password);
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUser.setLevel(false);
        tbUserService.insert(tbUser);
        model.addAttribute("message", "注册成功，请登录");
        return "login";
    }
}
