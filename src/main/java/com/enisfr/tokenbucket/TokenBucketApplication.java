package com.enisfr.tokenbucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TokenBucketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenBucketApplication.class, args);
	}

}
