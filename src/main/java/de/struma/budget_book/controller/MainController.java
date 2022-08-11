package de.struma.budget_book.controller;

import de.struma.budget_book.service.SparBuchungsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

	SparBuchungsService sparBuchungsService;

	public MainController(SparBuchungsService sparBuchungsService) {
		this.sparBuchungsService = sparBuchungsService;
	}

	@GetMapping(value = {"/home", "/"})
	public String showHome(Model model) {
		model.addAttribute("allMoneySavings", sparBuchungsService.getAlleEinsparungenInsgesamt());
		model.addAttribute("totalInventory", sparBuchungsService.getWertWarenbestand());
		return "Sides/Home/index";
	}
	

}
