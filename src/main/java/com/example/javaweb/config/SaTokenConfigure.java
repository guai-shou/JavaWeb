package com.example.javaweb.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.stp.StpInterface;
import com.example.javaweb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/29 18:52
 */
/**
 * Sa-Token代码方式进行配置
 */
@Configuration
public class SaTokenConfigure implements StpInterface {

    @Autowired
    private IAdminService adminService;

    // 获取配置Bean (以代码的方式配置Sa-Token, 此配置会覆盖yml中的配置)
    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName("satoken");             // token名称 (同时也是cookie名称)
        config.setTimeout(30 * 24 * 60 * 60);       // token有效期，单位s 默认30天
        config.setActivityTimeout(-1);              // token临时有效期 (指定时间内无操作就视为token过期) 单位: 秒
        config.setIsConcurrent(true);               // 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录)
        config.setIsShare(true);                    // 在多人登录同一账号时，是否共用一个token (为true时所有登录共用一个token, 为false时每次登录新建一个token)
        config.setTokenStyle("uuid");               // token风格
        config.setIsLog(false);                     // 是否输出操作日志
        //config.cookie.setSameSite("None");
        return config;
    }

    /* 返回一个账号所有拥有的权限集合 */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return adminService.getPermissionListById(loginId);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}

