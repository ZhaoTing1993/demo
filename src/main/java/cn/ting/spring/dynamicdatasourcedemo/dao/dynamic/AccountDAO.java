package cn.ting.spring.dynamicdatasourcedemo.dao.dynamic;

import cn.ting.spring.dynamicdatasourcedemo.model.Account;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO {

	public int addMoney(int userId, float money);
	
	public int minusMoney(int userId, float money);

	@CacheEvict(value = {"indexCache"},allEntries = true,beforeInvocation = true)
	public int insertAccount(Account account);

	@Cacheable(value = "indexCache",key = "'xmlgetAccountById'+#id")
	public Account getAccountById(int id);

	@Cacheable(value = "indexCache",key = "'xmlgetAccountByUserId'+#userId")
	public Account getAccountByUserId(int userId);
}
