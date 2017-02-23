package cn.ting.spring.dynamicdatasourcedemo.core.datasource;

import cn.ting.spring.dynamicdatasourcedemo.dao.IDynamicDataSourceDao;
import cn.ting.spring.dynamicdatasourcedemo.model.AdminDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
public class DynamicDataSourceManager implements ApplicationContextAware{

    Logger logger = LoggerFactory.getLogger(DynamicDataSourceManager.class);

    @Autowired
    private IDynamicDataSourceDao dynamicDataSourceDao;

    @Autowired
    private DynamicDataSource dynamicDataSource;
    //
    private Map<Object, Object> dataSourcePoolMap = new HashMap<Object , Object>();

    /**
     * 初始化加载创建数据源连接池
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("-------------->开始初始化加载创建动态数据源...");
        //获取所有数据源配置信息
        List<AdminDataSource> dataSourceList = dynamicDataSourceDao.listAdminDataSource();
        for(AdminDataSource adminDataSource : dataSourceList) {
            //
            createDataSourcePool(adminDataSource);
        }
        logger.info("-------------->初始化加载创建动态数据源完毕，加载数："+dataSourceList.size());
    }

    /**
     * 创建数据源连接池
     * @param adminDataSource
     */
    public void createDataSourcePool(AdminDataSource adminDataSource) {
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
        this.dataSourcePoolMap.put(adminDataSource.getDatasourceName(), DruidDataSource);
        dynamicDataSource.setTargetDataSources(dataSourcePoolMap);
    }

    /**
     * 获取数据源
     * @param datasourceName
     * @return
     */
    public JdbcTemplate getDataSourcePoolBySourceName(String  datasourceName) throws Exception {
        //
        DruidDataSource DruidDataSource = (DruidDataSource) this.dataSourcePoolMap.get(datasourceName);
        if(DruidDataSource == null) {
            logger.info(String.format("未找到[SourceID=%d]对应的数据源，则从数据库重新获取...", datasourceName));
            //未获取到相应的数据源，则从数据库重新获取，来创建新的数据源连接池
            AdminDataSource adminDataSource = dynamicDataSourceDao.getAdminDataSourceByName(datasourceName);
            if(adminDataSource == null) {
                logger.info(String.format("从数据库重新获取，未找到[SourceID=%d]对应的数据源", datasourceName));
                throw new Exception("未获取到匹配的动态数据源[ID="+datasourceName+"]");
            } else {
                //add
                createDataSourcePool(adminDataSource);
                //get
                DruidDataSource = (DruidDataSource) this.dataSourcePoolMap.get(datasourceName);
            }

        } else {
            logger.info(String.format("已找到[SourceID=%d]对应的数据源!", datasourceName));
        }
        //
        JdbcTemplate jdbcTempleDynamic = new JdbcTemplate(DruidDataSource);
        //
        return jdbcTempleDynamic;
    }

    /**
     * 关闭所有数据源连接池
     */
    public void close() {
        Set<Object > key = dataSourcePoolMap.keySet();
        for (Iterator it = key.iterator(); it.hasNext();) {
            DruidDataSource DruidDataSource = (DruidDataSource) dataSourcePoolMap.get(it.next());
            try {
                DruidDataSource.close();
            } catch (Exception e) {
                logger.error("关闭连接池异常：DruidDataSource="+DruidDataSource.getName());
                e.printStackTrace();
            }
        }
    }

}
