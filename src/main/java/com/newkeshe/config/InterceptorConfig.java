package com.newkeshe.config;

import com.newkeshe.interceptor.AdminInterceptor;
import com.newkeshe.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor())
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");
        registry.addInterceptor(adminInterceptor())
                .addPathPatterns("/admin/**");
    }
    @Bean
    public UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }
    @Bean
    public AdminInterceptor adminInterceptor(){
        return new AdminInterceptor();
    }
}
