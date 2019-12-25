package com.bill.springbootproject.config;

import com.bill.springbootproject.interceoter.LoginIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * 添加需要使用的拦截器
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    /**
     * 拦截未登录的用户
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginIntercepter()).addPathPatterns("/user/api/*/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
