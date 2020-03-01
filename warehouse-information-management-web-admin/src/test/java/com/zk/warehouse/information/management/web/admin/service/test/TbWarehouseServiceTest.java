package com.zk.warehouse.information.management.web.admin.service.test;

import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.domain.TbWarehouse;
import com.zk.warehouse.information.management.web.admin.dao.TbWarehouseDao;
import com.zk.warehouse.information.management.web.admin.service.TbWarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zk
 * @date 2020/2/22-17:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbWarehouseServiceTest {
    @Autowired
    private TbWarehouseService tbWarehouseService;

    @Test
    public void testSelectAll(){
        List<TbWarehouse> tbWarehouses = tbWarehouseService.selectAll();
        for (TbWarehouse tbWarehouse : tbWarehouses) {
            System.out.println(tbWarehouse.getSortOrder());
        }
    }

    @Test
    public void testGetNumber(){
        List<String> name = tbWarehouseService.getName();
        for (String s : name) {
            System.out.println(s);
        }
    }
}
