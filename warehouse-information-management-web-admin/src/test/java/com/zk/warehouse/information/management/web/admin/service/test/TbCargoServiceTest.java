package com.zk.warehouse.information.management.web.admin.service.test;

import com.zk.warehouse.information.management.domain.TbCargo;
import com.zk.warehouse.information.management.web.admin.dao.TbCargoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author zk
 * @date 2020/3/1-15:31
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbCargoServiceTest {
    @Autowired
    private TbCargoDao tbCargoDao;

    @Test
    public void testCountName(){
        System.out.println(tbCargoDao.countName("苹果"));
    }

    @Test
    public void testInsert(){
        TbCargo tbCargo = new TbCargo();
        tbCargo.setCreated(new Date());
        tbCargo.setUpdated(new Date());
        tbCargoDao.insert(tbCargo);
    }
}
