package com.qzy.springboot.config;

import com.qzy.springboot.servlet.CaptchaServlet;
import com.qzy.springboot.servlet.EmailServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServletConfig {
    @Bean
    public ServletRegistrationBean captchaServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean<>(new CaptchaServlet(), "/captcha");
        return bean;
    }

    @Bean
    public ServletRegistrationBean emailServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean<>(new EmailServlet(), "/email");
        return bean;
    }
}
