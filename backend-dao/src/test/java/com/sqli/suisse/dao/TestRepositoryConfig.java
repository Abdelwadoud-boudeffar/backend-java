package com.sqli.suisse.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EntityScan("com.sqli.suisse.dao.model")
@EnableJpaRepositories("com.sqli.suisse.dao.repository")
@ComponentScan("com.sqli.suisse.dao.repository")
public class TestRepositoryConfig {

}