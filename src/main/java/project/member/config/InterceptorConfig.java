package project.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.member.web.interceptor.LoginInterceptor;
import project.member.web.interceptor.StartInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new StartInterceptor())
        .order(1)
        .addPathPatterns("/**")
        .excludePathPatterns("/css")
        .excludePathPatterns("/favicon.ico")
        .excludePathPatterns("/error");

    registry.addInterceptor(new LoginInterceptor())
        .order(2)
        .addPathPatterns("/member/list");
  }
}
