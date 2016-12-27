package com.isa.analysis;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collection;

/**
 * @author zhaobing
 */
@SpringBootApplication
@Import(RepositoryRestMvcConfiguration.class)
public class SpringbootSdnEmbeddedApplication {

    public static void main(String[] args){
        SpringApplication.run(SpringbootSdnEmbeddedApplication.class, args);
    }
}
