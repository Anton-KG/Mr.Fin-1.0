package com.ej.router.configuration;

import com.ej.router.Application;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Configuration class for JPA.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = Application.class)
@ComponentScan({"com.ej.router.dao"})
public class JpaConfig implements TransactionManagementConfigurer {
    @Value("${dataSource.driverClassName}")
    private String driver;

    @Value("${dataSource.url}")
    private String url;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    /**
     * @return DataSource.
     * @throws HibernateException Exception HibernateException.
     */
    @Bean
    public DataSource dataSource() throws HibernateException {
        Properties dbcpProperties = new Properties();
        dbcpProperties.put("driverClassName", driver);
        dbcpProperties.put("url", url);
        dbcpProperties.put("username", username);
        dbcpProperties.put("password", password);

        try {
            return BasicDataSourceFactory.createDataSource(dbcpProperties);
        } catch (Exception e) {
            throw new HibernateException("Could not create a DBCP pool", e);
        }
    }

    /**
     * Configure EntityManager factory.
     *
     * @return EntityManagerFactory.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.ej.router");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);
        jpaProperties.put(org.hibernate.cfg.Environment.FORMAT_SQL, formatSql);
        jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, showSql);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }
}
