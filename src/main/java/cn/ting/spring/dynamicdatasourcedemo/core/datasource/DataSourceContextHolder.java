package cn.ting.spring.dynamicdatasourcedemo.core.datasource;

/**
 * Created by ZHAOTING001 on 2017/2/23.
 */
public class DataSourceContextHolder {

    private static final ThreadLocal contextHolder=new ThreadLocal();

    public static void setDataSourceType(String dataSourceName){
        contextHolder.set(dataSourceName);
    }

    public static String getDataSourceName(){
        return (String) contextHolder.get();
    }

    public static void clearDataSourceType(){
        contextHolder.remove();
    }

}
