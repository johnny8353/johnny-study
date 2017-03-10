package com.zte.msm.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
/**
我们就可以使用@ConfigurationProperties，它可以把同类的配置信息自动封装成实体类
如果发现@ConfigurationPropertie不生效，有可能是项目的目录结构问题，
你可以通过@EnableConfigurationProperties(ConnectionSettings.class)来明确指定需要用哪个实体类来装载配置信息。
 *
 */
// @ConfigurationProperties(prefix = "primary") 读取配置文件
//@EnableConfigurationProperties(value = {SqlSessionFactoryConfig.class})
@EnableTransactionManagement
@MapperScan(basePackages = {"com.zte.msm.frame.log.**.dao"},sqlSessionFactoryRef = "logSqlSessionFactory")
public class SqlSessionFactoryConfig {
	@Bean("sqlSessionFactory")
	public SqlSessionFactoryBean getSqlSessionFactoryBean(@Qualifier("primaryDataSource") DataSource dataSource){
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean;
	}
	@Bean("logSqlSessionFactory")
	public SqlSessionFactoryBean getLogSqlSessionFactoryBean(@Qualifier("logDataSource") DataSource dataSource){
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//		bean.setMapperLocations("");
		bean.setDataSource(dataSource);
		return bean;
	}
}
