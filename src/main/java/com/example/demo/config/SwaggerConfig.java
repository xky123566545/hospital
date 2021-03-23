package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {
        //配置了Swagger的Docket的bean示例
    @Bean
    public Docket docket4(Environment environment){
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("Authorization").description("认证token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
        //通过environment.acceptsProfiles判断是否处于自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)//enable是否启动swagger，如果为false，则Swagger不能在浏览器中访问
                .select()
                //RequestHandlerSelectors, 配置要扫描接口的方式
                //basePackage()扫描全部
                //any()扫描全部
                //none()不扫描
                //withClassAnnotation()扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                //path() 过滤什么路径
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);


    }
    //配置Swagger 信息=apiInfo
    private ApiInfo apiInfo() {
        //作者信息
        //Contact contact = new Contact("Xu", "https://blog.csdn.net/gjs935219", "1743429993@qq.com");
        return new ApiInfoBuilder()
                .title("医院管理系统")
                .description("医院管理系统Api")
                .version("v1.0")
                .build();
    }

}

