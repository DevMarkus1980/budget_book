package de.struma.budget_book;

import de.struma.budget_book.Service.RuntimeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@EnableScheduling
@SpringBootApplication
public class BudgetBookApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(BudgetBookApplication.class, args);

		new RuntimeService("http://localhost:8083").runBrowser();
	}

}
