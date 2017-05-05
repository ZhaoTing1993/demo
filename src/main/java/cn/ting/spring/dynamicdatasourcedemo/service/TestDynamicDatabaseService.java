package cn.ting.spring.dynamicdatasourcedemo.service;

import cn.ting.spring.dynamicdatasourcedemo.dao.dynamic.AccountDAO;
import cn.ting.spring.dynamicdatasourcedemo.model.Account;
import com.alibaba.fastjson.JSON;
import org.omg.CORBA.portable.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
@Service
public class TestDynamicDatabaseService {

    Logger logger = LoggerFactory.getLogger(TestDynamicDatabaseService.class);

    @Autowired
    private AccountDAO accountDAO;

    @Transactional
    public void test1() {
        logger.info("TestDynamicDatabaseService start...");

        logger.info("make account1 money +1");
        if (addMoneyTx(1, 1) > 0) {
            logger.info("money added");
        }

        if (true)
            throw new RuntimeException("artificial exc");

        logger.info("make account1 money +1");
        if (addMoneyTx(2, 1) > 0) {
            logger.info("money added");
        }

    }


    public int addMoneyTx(Integer userId, float money) {
        Account account = accountDAO.getAccountByUserId(1);
        logger.info("account:{}", JSON.toJSONString(account));
        return accountDAO.addMoney(userId, money);
    }
}
