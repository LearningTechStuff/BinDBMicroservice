package org.sharpsw.bindb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@ComponentScan
@EnableWebMvc
@SpringBootApplication
public class BinDbMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinDbMicroServiceApplication.class, args);
	}
}
