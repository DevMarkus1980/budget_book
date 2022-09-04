package de.struma.budget_book.moduls.budget_book.controller;

import de.struma.budget_book.moduls.budget_book.service.BuchungService;
import de.struma.budget_book.moduls.budget_book.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainControllerBudgetBook {

	BuchungService buchungService;
	StatisticService statisticService;


	public MainControllerBudgetBook(BuchungService buchungService, StatisticService statisticService) {
		this.buchungService = buchungService;
		this.statisticService = statisticService;
	}

	@GetMapping(value = {"/budget_book/"})
	public String showHome(Model model) {


		model.addAttribute("saldo", statisticService.getSaldo());
		return "Sides/Budget_Book/Home/budget_book";
	}
	

}
