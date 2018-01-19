package com.biarca.app.ws.web.layout;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ThymeleafLayoutInterceptor());
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/app/login").setViewName("signin/login");
    registry.addViewController("/app/dashboard").setViewName("user/dashboard");
    registry.addViewController("/app/users").setViewName("user/users");
    registry.addViewController("/app/services").setViewName("user/services");
    registry.addViewController("/app/about").setViewName("user/about");
    registry.addViewController("/app/contact").setViewName("user/contact");
  }
}
