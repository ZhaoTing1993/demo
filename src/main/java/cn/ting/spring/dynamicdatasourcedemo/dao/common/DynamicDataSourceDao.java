package cn.ting.spring.dynamicdatasourcedemo.dao.common;

import cn.ting.spring.dynamicdatasourcedemo.model.AdminDataSource;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
@Repository
public interface DynamicDataSourceDao {

    String SELECT = "SELECT datasource_name AS datasouceName," +
            "  url AS url," +
            "  username AS username," +
            "  password AS password " +
            "FROM ting_admin_datasource WHERE status=0";

    String GET_DATASOURCE_BY_NAME= "SELECT datasource_name AS datasouceName," +
            "  url AS url," +
            "  username AS username," +
            "  password AS password " +
            "FROM ting_admin_datasource WHERE status=0 AND datasource_name=#{datasourceName}";

    @Select(SELECT)
    List<AdminDataSource> listAdminDataSource();

    @Select(GET_DATASOURCE_BY_NAME)
    AdminDataSource getAdminDataSourceByName(String datasourceName);
}
