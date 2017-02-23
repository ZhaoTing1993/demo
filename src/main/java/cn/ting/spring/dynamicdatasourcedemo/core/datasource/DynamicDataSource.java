package cn.ting.spring.dynamicdatasourcedemo.core.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    protected Object determineCurrentLookupKey() {
        logger.info("database changed to:{}",DataSourceContextHolder.getDataSourceName());
        return DataSourceContextHolder.getDataSourceName();
    }

    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);

        super.afterPropertiesSet();
    }

}
