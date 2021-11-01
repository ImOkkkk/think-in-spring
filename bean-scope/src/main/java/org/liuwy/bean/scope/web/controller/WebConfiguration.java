package org.liuwy.bean.scope.web.controller;

import org.liuwy.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web MVC配置类
 * @author ImOkkkk
 * @date 2021/11/1 22:09
 * @since 1.0
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    @RequestScope
    public User user(){
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setName("周杰伦");
        return user;
    }
}
