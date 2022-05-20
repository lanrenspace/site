package com.lanrenspace.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@EnableR2dbcRepositories
@EnableWebFlux
@SpringBootApplication
public class SiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }
}
