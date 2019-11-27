package com.server.venus.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

/**
 * 项目名称：venus
 * 类名称：SwaggerConfig
 * 类描述：Swagger配置类
 * 创建人：yingx
 * 创建时间： 2019/10/29
 * 修改人：yingx
 * 修改时间： 2019/10/29
 * 修改备注：
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Value("${swagger2.enable}")
    private boolean swagger2Enable;

    @Bean
    public Docket createRestApi() {

        // 给全局head中添加公共参数token
        ParameterBuilder tokenPar = new ParameterBuilder();
        ArrayList<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("String")).parameterType("header").required(true).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                // .enable(swagger2Enable) // 用于控制swagger是否加载，用于生产环境中进行对swagger的关闭
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) // 采用包含注解的方式来确定要显示的接口
                //.apis(RequestHandlerSelectors.basePackage("com.swagger.wen.controller")) // 采用扫描包的方式来确定要显示的接口
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("启明星接口文档")
                .description("RESTful APIs")
                .termsOfServiceUrl("http://localhost:10001/")
                .contact("yxwenqiyu@163.com")
                .version("1.0.0")
                .build();
    }
}
