package com.socket.version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(VersionApplication.class, args);
	}

}
