package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.example.demo.mybatis.mapper")
//@ServletComponentScan("com.example.demo.filter")
public class LkMybatisXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LkMybatisXmlApplication.class, args);
	}

}
