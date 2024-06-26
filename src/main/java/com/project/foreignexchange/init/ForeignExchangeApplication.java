package com.project.foreignexchange.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("com.project.foreignexchange.*")
@EntityScan("com.project.foreignexchange.*")
@EnableJpaRepositories("com.project.foreignexchange.*")
@EnableFeignClients("com.project.foreignexchange.*")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class ForeignExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForeignExchangeApplication.class, args);
	}

}
