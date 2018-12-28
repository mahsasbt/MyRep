package com.sbt.springBoot;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = { "com.sbt.springBoot.persistence.dao" })
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class PersistenceContextConfig {

}