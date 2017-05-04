package cn.ting.spring.dynamicdatasourcedemo.core.datasource;

import cn.ting.spring.dynamicdatasourcedemo.dao.common.DynamicDataSourceDao;
import cn.ting.spring.dynamicdatasourcedemo.model.AdminDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    @Autowired
    private DynamicDataSourceDao dynamicDataSourceDao;

    protected Object determineCurrentLookupKey() {
        logger.info("database changed to:{}",DataSourceContextHolder.getDataSourceName());
        return DataSourceContextHolder.getDataSourceName();
    }

    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);

        super.afterPropertiesSet();
    }

    public void initDataSouce(){
        logger.info("-------------->开始初始化加载创建动态数据源...");
        //获取所有数据源配置信息
        List<AdminDataSource> dataSourceList = dynamicDataSourceDao.listAdminDataSource();
        Map<Object,Object> dataSourcePoolMap = new HashMap();
        for(AdminDataSource adminDataSource : dataSourceList) {
            //
            DruidDataSource DruidDataSource = new DruidDataSource();
            try {
                DruidDataSource.setDriverClassName(adminDataSource.getDriverClassName());
                DruidDataSource.setUrl(adminDataSource.getUrl());
                DruidDataSource.setUsername(adminDataSource.getUsername());
                DruidDataSource.setPassword(adminDataSource.getPassword());
                //
                DruidDataSource.setInitialSize(adminDataSource.getInitialSize());
                DruidDataSource.setMinIdle(adminDataSource.getMinIdle());
                DruidDataSource.setMaxActive(adminDataSource.getMaxActive());

            } catch (DataSourceException e) {
                e.printStackTrace();
            }
            dataSourcePoolMap.put(adminDataSource.getDatasourceName(), DruidDataSource);

        }
        this.setTargetDataSources(dataSourcePoolMap);
        logger.info("-------------->初始化加载创建动态数据源完毕，加载数："+dataSourceList.size());
    }

    @Override
    public void afterPropertiesSet(){
        this.initDataSouce();
    }

}
