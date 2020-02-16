package com.zk.warehouse.information.management.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zk
 * @date 2020/1/15-15:38
 */
@Controller
public class MainController {

    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }
}
