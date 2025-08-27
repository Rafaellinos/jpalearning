package br.com.rafaellinos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = JpalearningApplication.class)
public class JpalearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpalearningApplication.class, args);
	}

}
