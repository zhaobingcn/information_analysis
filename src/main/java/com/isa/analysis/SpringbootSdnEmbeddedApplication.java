package com.isa.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zhaobing
 */
@SpringBootApplication

public class SpringbootSdnEmbeddedApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args){
        SpringApplication.run(SpringbootSdnEmbeddedApplication.class, args);
    }
}
