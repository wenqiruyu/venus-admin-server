package com.server.venus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 项目名称：venus
 * 类名称：MvcConfig
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/29
 * 修改人：yingx
 * 修改时间： 2019/10/29
 * 修改备注：
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${swagger2.enable}")
    private boolean swagger2Enable;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (this.swagger2Enable) {
            registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/info");
    }
}
