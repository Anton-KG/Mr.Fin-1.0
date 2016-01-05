package com.ej.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main container for the Spring Boot application.
 *
 */
@Configuration
@ComponentScan({"com.ej.cache.core", "com.ej.cache.services", "com.ej.cache.controller"})
@SpringBootApplication
public class MainContainer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainContainer.class);
    }

    /**
     * Main method of the class.
     *
     * @param args arguments for the command line.
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setWebEnvironment(false);
        application.run(MainContainer.class, args);
    }

}
