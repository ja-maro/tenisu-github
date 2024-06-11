package com.ja.tenisu;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Application {

	@GetMapping("/")
	public String home() {
		return "Welcome to Tenisu !\nLearn more about players with /players";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
