package com.arano.config;


import com.arano.constant.WebConstant;
import com.arano.filter.WrapRealUriRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppWebMvcConfigurer  {
    @Bean
    public FilterRegistrationBean<WrapRealUriRequestFilter> realUriRequestFilterRegistrationBean() {
        FilterRegistrationBean<WrapRealUriRequestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.addUrlPatterns(WebConstant.URI_PORTAL);
        filterRegistrationBean.setFilter(realUriRequestFilter());
        return filterRegistrationBean;
    }
    @Bean
    public WrapRealUriRequestFilter realUriRequestFilter() {
        return new WrapRealUriRequestFilter();
    }

}
