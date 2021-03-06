package com.my.project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-07
 **/
@ComponentScan({"com.my.framework", "com.my.project"})
@MapperScan({"com.my.framework.datasource.service", "com.my.project.dao"})
@EnableWebMvc
@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}
