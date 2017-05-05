package cn.ting.spring.dynamicdatasourcedemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by ZHAOTING001 on 2017/5/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestControllerTest {

    @Autowired
    private TestController testController;

    @Test
    public void test1() throws Exception {
        testController.test1(null,null);
    }

}