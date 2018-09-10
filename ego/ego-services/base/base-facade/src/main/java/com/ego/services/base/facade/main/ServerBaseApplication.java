package com.ego.services.base.facade.main;

import com.ebase.core.EnvironmentUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: wangyu
 */
@SpringBootApplication(scanBasePackages = "com.ego.services.base.facade")
//@ImportResource({ "classpath*:/META-INF/spring/*.xml" })
//@EnableEurekaClient
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
//事务
@EnableTransactionManagement
@MapperScan({"com.ego.services.base.facade.dao", "com.ego.services.base.facade**.mapper"})
@ComponentScan(basePackages = {"com.ebase.core.cache", "com.ego.services.base.facade","com.ebase.core.conf"})
//断路器
@EnableCircuitBreaker
@EnableScheduling
public class ServerBaseApplication {


    private static Logger logger = LoggerFactory.getLogger(ServerBaseApplication.class);

    public static void main(String[] args) {
        EnvironmentUtil.setSystemEnv(args);
        SpringApplication application = new SpringApplication(ServerBaseApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        logger.info("service-base start success");
    }

}
