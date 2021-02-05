package com.example.demo.filter.jsonconvert;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> registFilter1(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>();
        registrationBean.setFilter(new JsonConvertFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("Filter1");
        registrationBean.setOrder(1);
        return registrationBean;
    } 
}