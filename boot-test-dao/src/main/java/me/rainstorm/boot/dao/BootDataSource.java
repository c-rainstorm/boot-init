package me.rainstorm.boot.dao;

import me.rainstorm.boot.dao.config.BootDataSourceConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Configuration(value = "bootMybatisConfig")
@MapperScan(basePackages = {"me.rainstorm.boot.dao.boot"},
        sqlSessionFactoryRef = "bootSqlSessionFactory")
public class BootDataSource {

    @Resource
    private BootDataSourceConfig bootDataSourceConfig;

    @Bean
    public DataSource bootDataSource() {
        return DataSourceBuilder.create().driverClassName(bootDataSourceConfig.getDriver())
                .url(bootDataSourceConfig.getUrl())
                .username(bootDataSourceConfig.getUsername())
                .password(bootDataSourceConfig.getPassword()).build();
    }

    @Bean
    public SqlSessionFactory bootSqlSessionFactory()
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(bootDataSource());
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().
                        getResources(bootDataSourceConfig.getMapperLocation()));
//        bean.setConfigLocation(new PathMatchingResourcePatternResolver().
//                getResource("classpath:mybatis-config.xml"));
        return bean.getObject();
    }
}
