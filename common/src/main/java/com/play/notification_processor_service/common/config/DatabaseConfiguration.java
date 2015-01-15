package com.play.notification_processor_service.common.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration  {
/*
    private RelaxedPropertyResolver jpaPropertyResolver;
    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    @Override
    public void setEnvironment(Environment environment) {
        this.jpaPropertyResolver = new RelaxedPropertyResolver(environment, "spring.jpa.");
    }*/

//    @Bean
//    @DependsOn("jdbcTemplate")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        if (persistenceUnitManager != null) {
//            entityManagerFactoryBean
//                    .setPersistenceUnitManager(persistenceUnitManager);
//        }
//        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//        entityManagerFactoryBean.setDataSource(dataSource);
//        entityManagerFactoryBean.setPackagesToScan("com.play.notification_processor_service");
//        entityManagerFactoryBean.getJpaPropertyMap().putAll(jpaPropertyResolver.getSubProperties("properties."));
//        Map<String, Object> properties = entityManagerFactoryBean.getJpaPropertyMap();
//        properties.put("hibernate.ejb.naming_strategy", jpaPropertyResolver.getProperty("hibernate.naming-strategy", SpringNamingStrategy.class.getName()));
//        properties.put("hibernate.hbm2ddl.auto", jpaPropertyResolver.getProperty("hibernate.ddl-auto", "none"));
//        return entityManagerFactoryBean;
//    }
}