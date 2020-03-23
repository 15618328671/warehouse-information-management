package com.zk.warehouse.information.management.web.admin.service.test;

import com.zk.warehouse.information.management.commons.utils.RegexUtils;
import org.junit.Test;

/**
 * @author zk
 * @date 2020/3/23-15:45
 */
public class PhoneTest {
    @Test
    public void test(){
        System.out.println("15618328671".matches("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$"));
    }
}
