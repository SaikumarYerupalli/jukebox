package com.innoventes.jukebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages="com.innoventes.jukebox.repo")
@EntityScan(basePackages="com.innoventes.jukebox.model")
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages={"com.innoventes.jukebox"})
public class JukeboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(JukeboxApplication.class, args);
	}

}
