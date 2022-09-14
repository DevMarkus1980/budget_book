package de.struma.budget_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BudgetBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetBookApplication.class, args);
	}

}
