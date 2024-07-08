package com.wallet.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	@Configuration
	@EnableConfigurationProperties
	public class Config {

		@Bean
		@ConfigurationProperties
		public DockerComposeProperties dockerComposeProperties() {
			return new DockerComposeProperties("C:/Users/mi/Desktop/java/JavaCode Test/test/docker-compose.yml");
		}
	}
}
