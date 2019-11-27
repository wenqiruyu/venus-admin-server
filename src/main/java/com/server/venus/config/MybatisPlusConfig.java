package com.server.venus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.server.venus.interceptor.CustomMetaObjectHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 项目名称：venus
 * 类名称：MybatisPlusConfig
 * 类描述：MybatisPlus配置类
 * 创建人：yingx
 * 创建时间： 2019/10/24
 * 修改人：yingx
 * 修改时间： 2019/10/24
 * 修改备注：
 */
@Configuration
@MapperScan("com.server.venus.mapper")
public class MybatisPlusConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomMetaObjectHandler customMetaObjectHandler;

    /**
     * mybatis分页插件
     *
     * @param
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @author yingx
     * @date 2019/10/21
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        //PerformanceInterceptor(),OptimisticLockerInterceptor()
        //添加分页功能
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfig());
        return sqlSessionFactory.getObject();
    }

    /**
     * mybatis全局配置，可在yml中配置
     *
     * @param
     * @return org.mockito.internal.configuration.GlobalConfiguration
     * @author yingx
     * @date 2019/10/21
     */
    @Bean
    public GlobalConfig globalConfig() {

        // 配置主键类型 AUTO:"数据库ID自增", INPUT:"用户输入ID",ID_WORKER:"全局唯一ID (数字类型唯一ID)", UUID:"全局唯一ID UUID";
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setDbType(DbType.MYSQL);
        dbConfig.setIdType(IdType.AUTO);
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setDbConfig(dbConfig);
        //刷新配置
//		globalConfig.setRefresh(true);
        globalConfig.setMetaObjectHandler(customMetaObjectHandler);

        return globalConfig;
    }
}
