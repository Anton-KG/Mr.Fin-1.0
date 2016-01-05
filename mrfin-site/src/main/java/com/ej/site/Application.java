package com.ej.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration for the Spring Boot application.
 */
@SpringBootApplication
@ComponentScan({"com.ej.site.controller"})
@PropertySource("classpath:/application.properties")
public class Application extends SpringBootServletInitializer {

    /**
     * The main method.
     *
     * @param args The list of argument.
     */
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setWebEnvironment(false);
        application.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
