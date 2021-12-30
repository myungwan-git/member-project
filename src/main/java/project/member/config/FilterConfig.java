package project.member.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.member.web.filter.LoginFilter;
import project.member.web.filter.StartFilter;

import javax.servlet.Filter;

//@Configuration
  public class FilterConfig {

  @Bean
  public FilterRegistrationBean startFilterAdd() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new StartFilter());
    filterRegistrationBean.setOrder(1);
    filterRegistrationBean.addUrlPatterns("/*");

    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean loginFilterAdd() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new LoginFilter());
    filterRegistrationBean.setOrder(2);
    //로그인 필터의 세션검사는 /member/list 경로에만 적용.
    filterRegistrationBean.addUrlPatterns("/member/list");

    return filterRegistrationBean;
  }

}
