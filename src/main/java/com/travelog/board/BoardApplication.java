package com.travelog.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableDiscoveryClient
class BoardApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
