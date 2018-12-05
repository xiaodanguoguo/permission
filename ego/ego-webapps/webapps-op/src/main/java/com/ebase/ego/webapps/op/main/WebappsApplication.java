package com.ebase.ego.webapps.op.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.ebase.core.EnvironmentUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@SpringCloudApplication
//断路器
@EnableCircuitBreaker
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@ComponentScan(basePackages = { "com.ebase.ego.webapps.op","com.ebase.core.fastdfs", "com.github.tobato.fastdfs","com.ebase.core.cache","com.ebase.core.conf"})
@EnableFeignClients(basePackages = { "com.ego.services.juri.api","com.ego.services.message.api" })
@EnableDiscoveryClient
@EnableEurekaClient

public class WebappsApplication  extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebappsApplication.class);
	}

	public static void main(String[] args) {
		//长短链接
//		System.setProperty("http.keepAlive", "false");
		System.setProperty("server.TYPE", "webapp_op");
		EnvironmentUtil.setSystemEnv(args);

		SpringApplication.run(WebappsApplication.class, args);
	}
	

	@LoadBalanced
	@Bean(name = "restTemplate")
	RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
