package cn.ting.spring.dynamicdatasourcedemo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestDynamicDatabaseServiceTest {

    @Autowired
    private TestDynamicDatabaseService testDynamicDatabaseService;

    @Test
    public void testITestXmlDAO() throws Exception {
        testDynamicDatabaseService.testITestXmlDAO();

    }

}