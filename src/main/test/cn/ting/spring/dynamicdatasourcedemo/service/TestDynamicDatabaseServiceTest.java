package cn.ting.spring.dynamicdatasourcedemo.service;


import cn.ting.spring.dynamicdatasourcedemo.core.datasource.DataSourceContextHolder;
import cn.ting.spring.dynamicdatasourcedemo.dao.dynamic.AccountDAO;
import cn.ting.spring.dynamicdatasourcedemo.model.Account;
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

    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void testaccountDAO() throws Exception {

        System.out.println("set database to test1");
        DataSourceContextHolder.setDataSourceType("test1");
        if (accountDAO.getAccountByUserId(1) == null) {
            Account account1 = new Account();
            account1.setUserId(1);
            account1.setMoney(100);
            accountDAO.insertAccount(account1);
        }
        accountDAO.addMoney(1, 1);

        System.out.println("set database to test2");
        DataSourceContextHolder.setDataSourceType("test2");
        if (accountDAO.getAccountByUserId(2) == null) {
            Account account2 = new Account();
            account2.setUserId(2);
            account2.setMoney(100);
            accountDAO.insertAccount(account2);
        }
        accountDAO.addMoney(2, 2);


    }

}