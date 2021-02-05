package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.MyFilter1;
import com.example.demo.filter.MyFilter2;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<MyFilter1> registFilter1(){
        FilterRegistrationBean<MyFilter1> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyFilter1());
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.setName("Filter1");
        registrationBean.setOrder(1);
        return registrationBean;
    }
//    @Bean
//    public FilterRegistrationBean<MyFilter2> registFilter2(){
//        FilterRegistrationBean<MyFilter2> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new MyFilter2());
//        registrationBean.addUrlPatterns("/org/*");
//        registrationBean.setName("Filter2");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }
}