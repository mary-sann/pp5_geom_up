package com.example.pp5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Pp5Application {

	public static void main(String[] args) {
		//「画面なんて無いから探さなくていいよ」という設定
		System.setProperty("java.awt.headless", "true");
		SpringApplication.run(Pp5Application.class, args);
	}

}
