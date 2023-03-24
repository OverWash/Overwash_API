package com.meta.overwash.filter;

import com.meta.overwash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FilterConfig {
    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @Bean
    public FilterRegistrationBean<MyFilter> filter1(){
        FilterRegistrationBean<MyFilter> myFilterBean =
                new FilterRegistrationBean<>(new MyFilter(env));
        myFilterBean.addUrlPatterns("/user2");
        myFilterBean.addUrlPatterns("/hello/*");
        myFilterBean.addUrlPatterns("/member/1");
        myFilterBean.setOrder(0);

        return myFilterBean;
    }

}
