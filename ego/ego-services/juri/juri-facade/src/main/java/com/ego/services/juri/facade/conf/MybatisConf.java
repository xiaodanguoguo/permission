package com.ego.services.juri.facade.conf;

import com.ego.services.juri.facade.filter.LoginStatusFilter;
import com.github.pagehelper.PageHelper;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Properties;

@Configuration
public class MybatisConf extends WebMvcConfigurerAdapter {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("dialect","mysql");
        pageHelper.setProperties(p);
        return pageHelper;
    }

//    /**
//     * feign请求拦截器
//     *
//     * @return
//     */
//    @Bean
//    public RequestInterceptor requestInterceptor(){
//        return new LoginStatusFilter();
//    }
}