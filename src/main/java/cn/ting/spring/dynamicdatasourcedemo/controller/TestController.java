package cn.ting.spring.dynamicdatasourcedemo.controller;

import cn.ting.spring.dynamicdatasourcedemo.core.datasource.DataSourceContextHolder;
import cn.ting.spring.dynamicdatasourcedemo.service.TestDynamicDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
@Controller
@RequestMapping(value = "/")
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestDynamicDatabaseService testDynamicDatabaseService;

    @RequestMapping("test1.do")
    @ResponseBody
    public String test1(HttpServletRequest request, HttpServletResponse response){

        logger.info("test 1 start...");

        logger.info("set database to test1");
        DataSourceContextHolder.setDataSourceType("test1");

        try {
            testDynamicDatabaseService.test1();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "test1";
    }
}
