package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan({"com.example.demo.area.mapper","com.example.demo.picture.mapper",
              "com.example.demo.registration.mapper","com.example.demo.department.mapper",
                "com.example.demo.user.mapper","com.example.demo.login.mapper"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
