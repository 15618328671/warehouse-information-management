package com.zk.warehouse.information.management.web.admin.service.test;

import com.zk.warehouse.information.management.domain.TbAdministrator;
import com.zk.warehouse.information.management.web.admin.service.TbAdministratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author zk
 * @date 2020/1/18-15:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbAdministratorServiceTest {
    @Autowired
    private TbAdministratorService tbAdministratorService;

    @Test
    public void testSelectAll(){
        List<TbAdministrator> tbAdministrators = tbAdministratorService.selectAll();
        for (TbAdministrator tbAdministrator : tbAdministrators) {
            System.out.println(tbAdministrator.getUsername());
        }
    }

    @Test
    public void testMd5(){
        System.out.println(DigestUtils.md5DigestAsHex("admin".getBytes()));
    }

    @Test
    public void testInsert(){
        TbAdministrator tbAdministrator = new TbAdministrator();
        tbAdministrator.setUsername("朱凯");
        tbAdministrator.setPassword("123");
        tbAdministrator.setPhone("123");
        tbAdministrator.setEmail("123");
        tbAdministrator.setCreated(new Date());
        tbAdministrator.setUpdated(new Date());

        tbAdministratorService.insert(tbAdministrator);
    }
}
