package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

/**
 * 类说明
 * 
 * @author likui
 * @since 2021年2月1日 下午3:56:22
 * @version 1.0
 *
 */
//@Order(1)
//@WebFilter(urlPatterns={"/user"})
public class MyFilter1 implements Filter {
	private static final Logger log = LoggerFactory.getLogger(MyFilter1.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("初始化过滤器MyFilter1");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("进入目标资源之前MyFilter1先干点啥");
		chain.doFilter(request, response);
		log.info("MyFilter1处理一下服务端返回的response");
	}

	@Override
	public void destroy() {
		 log.info("MyFilter1过滤器被销毁了");
	}

}
