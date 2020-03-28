package com.yong.demo.config;

import com.yong.demo.pojo.User;
import com.yong.demo.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 */
@Configuration
public class ShiroConfig {
    //装配自定义realm
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
    //配置安全管理器
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //注入自定义realm
        securityManager.setRealm(userRealm());
        return securityManager;
    }//LinkedHashMap有顺序且保证唯一
    //配置权限过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //注入安全管理器
        filterFactoryBean.setSecurityManager(securityManager());
        //未认证跳转
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setSuccessUrl("/index");
        filterFactoryBean.setUnauthorizedUrl("/unAuth");

        //配置安全过滤链
        Map<String,String> chain=new LinkedHashMap<>();
        //chain.put("/login","anon");
        chain.put("/loginAction","anon");
        chain.put("/css/**","anon");
        chain.put("/js/**","anon");
        chain.put("/img/**","anon");
        chain.put("/perm1","perms[睡觉]");
        chain.put("/perm2","perms[打游戏]");
        chain.put("/perm3","perms[看电影]");
        chain.put("/**","authc");
        filterFactoryBean.setFilterChainDefinitionMap(chain);
        return filterFactoryBean;
    }

    //启用Shiro内部Bean生命周期管理
    /*@Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return lifecycleBeanPostProcessor();
    }*/
    //启用AOP代理
   /* @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }*/
    //启用Shiro注解
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        //注入安全管理器
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
}
