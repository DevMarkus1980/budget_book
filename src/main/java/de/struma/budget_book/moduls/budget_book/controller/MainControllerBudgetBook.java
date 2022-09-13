package de.struma.budget_book.moduls.budget_book.controller;

import de.struma.budget_book.Service.AnzeigenService;
import de.struma.budget_book.moduls.budget_book.service.BuchungService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainControllerBudgetBook {

	BuchungService buchungService;
	AnzeigenService anzeigenService;


	public MainControllerBudgetBook(BuchungService buchungService, AnzeigenService anzeigenService) {
		this.buchungService = buchungService;
		this.anzeigenService = anzeigenService;
	}

	@GetMapping(value = {"/budget_book/"})
	public String showHome(Model model) {

		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "Sides/Budget_Book/Home/budget_book";
	}
	

}
