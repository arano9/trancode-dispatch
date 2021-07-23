package com.arano;

import com.arano.constant.WebConstant;
import com.arano.filter.WrapRealUriRequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TranCodeDispatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TranCodeDispatchApplication.class, args);
    }


}
