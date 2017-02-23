package cn.ting.spring.dynamicdatasourcedemo.service;

import cn.ting.spring.dynamicdatasourcedemo.core.datasource.DataSourceContextHolder;
import cn.ting.spring.dynamicdatasourcedemo.dao.ITestXmlDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
@Service
public class TestDynamicDatabaseService {

    Logger logger = LoggerFactory.getLogger(TestDynamicDatabaseService.class);

    @Autowired
    private ITestXmlDAO iTestXmlDAO;

    public void testITestXmlDAO(){
        logger.info("TestDynamicDatabaseService start...");

        try {
            logger.info("set database to test1");
            DataSourceContextHolder.setDataSourceType("test1");
            iTestXmlDAO.addMoney(1,1);
            logger.info("set database to test2");
            DataSourceContextHolder.setDataSourceType("test2");
            iTestXmlDAO.addMoney(2,2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
