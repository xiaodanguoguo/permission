package com.ego.services.juri.facade.main;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/**
 * Druid的DataResource配置类
 * 凡是被Spring管理的类，实现接口 EnvironmentAware 重写方法 setEnvironment 可以在工程启动时，
 * 获取到系统环境变量和application配置文件中的变量。 还有一种方式是采用注解的方式获取 @value("${变量的key值}")
 * 获取application配置文件中的变量。 这里采用第一种要方便些
 * Created by sun on 2017-1-20.
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig {
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driverClassName}")
	private String driver;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.initialSize}")
	private String initialSize;
	@Value("${spring.datasource.minIdle}")
	private String minIdle;
	@Value("${spring.datasource.maxWait}")
	private String maxWait;
	@Value("${spring.datasource.maxActive}")
	private String maxActive;
	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private String minEvictableIdleTimeMillis;
//	private static Logger Logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);
//	private RelaxedPropertyResolver propertyResolver;
	/**
	 * 加密key
	 */

//	public void setEnvironment(Environment env) {
//		this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
//	}

	@Bean
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(url);
		datasource.setDriverClassName(driver);
		datasource.setUsername(username);
		//TODO 数据库密码统一加密处理
		datasource.setPassword(password);
		datasource.setInitialSize(Integer.valueOf(initialSize));
		datasource.setMinIdle(Integer.valueOf(minIdle));
		datasource.setMaxWait(Long.valueOf(maxWait));
		datasource.setMaxActive(Integer.valueOf(maxActive));
		datasource.setMinEvictableIdleTimeMillis(
				Long.valueOf(minEvictableIdleTimeMillis));
		try {
			datasource.setFilters("stat,wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}//mybatis.mapper-locations
		return datasource;
	}

	// 按照BeanId来拦截配置 用来bean的监控
	@Bean(name = "druid-stat-interceptor")
	public DruidStatInterceptor DruidStatInterceptor() {
		DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
		return druidStatInterceptor;
	}

	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setProxyTargetClass(true);
		// 设置要监控的bean的id
		// beanNameAutoProxyCreator.setBeanNames("sysRoleMapper","loginController");
		beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");
		return beanNameAutoProxyCreator;
	}
}
