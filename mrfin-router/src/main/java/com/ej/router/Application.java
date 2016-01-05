package com.ej.router;

import com.ej.router.configuration.BeanConfig;
import com.ej.router.configuration.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Application configuration.
 */
@SpringBootApplication
@Import({BeanConfig.class, JpaConfig.class})
@PropertySource({
        "classpath:/application.properties",
        "classpath:/datasource.properties"
})
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
