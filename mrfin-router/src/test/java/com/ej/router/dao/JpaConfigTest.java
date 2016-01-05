package com.ej.router.dao;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@ComponentScan({"com.ej.router.dao", "com.ej.router.entity"})
public class JpaConfigTest implements TransactionManagementConfigurer {
    private static final String DRIVER = "org.hsqldb.jdbcDriver";
    private static final String URL = "jdbc:hsqldb:mem:mrfindb";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static final String DIALECT = "org.hibernate.dialect.HSQLDialect";
    private static final String HBM_2_DDL_AUTO = "create";
    private static final String SHOW_SQL = "true";

    /**
     * @return DataSource.
     * @throws HibernateException Exception HibernateException.
     */
    @Bean
    public DataSource dataSource() throws HibernateException {
        Properties dbcpProperties = new Properties();
        dbcpProperties.put("driverClassName", DRIVER);
        dbcpProperties.put("url", URL);
        dbcpProperties.put("username", USERNAME);
        dbcpProperties.put("password", PASSWORD);
        dbcpProperties.put("show_sql", SHOW_SQL);

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
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, DIALECT);
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, HBM_2_DDL_AUTO);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }
}
