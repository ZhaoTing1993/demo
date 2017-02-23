package cn.ting.spring.dynamicdatasourcedemo.dao;

import cn.ting.spring.dynamicdatasourcedemo.model.AdminDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
@Repository
public interface IDynamicDataSourceDao {

    List<AdminDataSource> listAdminDataSource();

    AdminDataSource getAdminDataSourceByName(String datasourceName);
}
