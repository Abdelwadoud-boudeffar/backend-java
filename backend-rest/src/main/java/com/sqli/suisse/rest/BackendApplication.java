package com.sqli.suisse.rest;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.sqli.suisse.dto.UserCreateDTO;
import com.sqli.suisse.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author ABDELWADOUD BOUDEFFAR
 * @since 1.0
 */

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"com.sqli.suisse.service", "com.sqli.suisse.rest.controller"})
@EntityScan("com.sqli.suisse.dao.model")
@EnableJpaRepositories("com.sqli.suisse.dao.repository")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner createInitialUsers(UserService userService, DataSource dataSource) {
		return (args) -> {
			UserCreateDTO user = new UserCreateDTO();
			user.setUserName("User1");
			user.setFirstName("Abdel1");
			user.setLastName("Boudeffar1");

			userService.createUser(user);

			UserCreateDTO user2 = new UserCreateDTO();
			user2.setUserName("User2");
			user2.setFirstName("Abdel2");
			user2.setLastName("Boudeffar2");

			userService.createUser(user2);

			UserCreateDTO user3 = new UserCreateDTO();
			user3.setUserName("User3");
			user3.setFirstName("Abdel3");
			user3.setLastName("Boudeffar3");

			userService.createUser(user3);
		};
	}

}
