package de.struma.budget_book;

import de.struma.budget_book.Service.FirstSetupService;
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
