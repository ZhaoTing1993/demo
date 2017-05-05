package cn.ting.spring.dynamicdatasourcedemo.service;

import cn.ting.spring.dynamicdatasourcedemo.dao.dynamic.AccountDAO;
import cn.ting.spring.dynamicdatasourcedemo.model.Account;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
@Service
public class TestDynamicDatabaseService {

    Logger logger = LoggerFactory.getLogger(TestDynamicDatabaseService.class);

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountService accountService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void test1() {
        logger.info("TestDynamicDatabaseService start...");

        // add money of account1 in current transaction
        logger.info("make account1 money +1");
        if (addMoney(1, 1) > 0) {
            logger.info("money added");
        }

        // add money of account2 in a new transaction and make artificial exception.
        try {
            logger.info("make account2 money +1");
            if (accountService.addMoneyTx(2, 1) > 0) {
                logger.info("money added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public int addMoney(Integer userId, float money) {
        Account account = accountDAO.getAccountByUserId(userId);
        logger.info("account:{}", JSON.toJSONString(account));
        return accountDAO.addMoney(userId, money);
    }



}
