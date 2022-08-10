package de.struma.budget_book.controller;

import de.struma.budget_book.service.SparBuchungsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequestMapping(value = {"/home", "/"})
@RestController
public class MainController {

	SparBuchungsService sparBuchungsService;

	public MainController(SparBuchungsService sparBuchungsService) {
		this.sparBuchungsService = sparBuchungsService;
	}

	@GetMapping
	public ModelAndView showHome(Model model) {
		model.addAttribute("allMoneySavings", sparBuchungsService.getAlleEinsparungenInsgesamt());
		model.addAttribute("totalInventory", sparBuchungsService.getWertWarenbestand());
		return new ModelAndView("Sides/Home/index");
	}
	

}
