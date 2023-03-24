package com.meta.overwash.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FilterConfig {
    @Autowired
    private Environment env;

    @Bean
    public FilterRegistrationBean<MyFilter> filter1(){

        FilterRegistrationBean<MyFilter> myFilterBean =
                new FilterRegistrationBean<>(new MyFilter(env));
        myFilterBean.addUrlPatterns("/user2");
        myFilterBean.addUrlPatterns("/hello/*");
        myFilterBean.setOrder(0);

        return myFilterBean;
    }

}
