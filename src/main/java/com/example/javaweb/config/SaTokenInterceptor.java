package com.example.javaweb.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/25 12:44
 */
@Configuration
public class SaTokenInterceptor implements WebMvcConfigurer {
    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        //registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**")
        //    .excludePathPatterns("/user/login")
        //    .excludePathPatterns("/user/logout")
        //    .excludePathPatterns("/user/register");
    //}


}
