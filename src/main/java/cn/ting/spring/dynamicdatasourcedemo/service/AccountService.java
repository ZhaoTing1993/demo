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
 * Created by ZHAOTING001 on 2017/5/5.
 */
@Service
public class AccountService {
    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountDAO accountDAO;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addMoneyTx(Integer userId, float money) {
        Account account = accountDAO.getAccountByUserId(userId);
        logger.info("account:{}", JSON.toJSONString(account));
        int i = accountDAO.addMoney(userId, money);
        if (true)
            throw new RuntimeException("artificial exc");
        return i;
    }

}
