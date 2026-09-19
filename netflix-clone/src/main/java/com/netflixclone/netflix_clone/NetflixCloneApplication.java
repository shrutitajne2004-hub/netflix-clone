package com.netflixclone.netflix_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NetflixCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixCloneApplication.class, args);
	}

}
