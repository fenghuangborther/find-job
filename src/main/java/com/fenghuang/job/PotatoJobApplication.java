package com.fenghuang.job;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class PotatoJobApplication {

    public static void main(String[] args) {
        SpringApplication.run( PotatoJobApplication.class, args );
    }

}
