package de.struma.budget_book.moduls.spar.controller;

import de.struma.budget_book.moduls.spar.service.BuchungsKalkulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

	BuchungsKalkulationService buchungsKalkulationService;

	public MainController(BuchungsKalkulationService buchungsKalkulationService) {
		this.buchungsKalkulationService = buchungsKalkulationService;
	}

	@GetMapping(value = {"/home", "/"})
	public String showHome(Model model) {
		buchungsKalkulationService.initCalkAllEntries();

		model.addAttribute("allMoneySavings", buchungsKalkulationService.getAlleEinsparungenInsgesamt());
		model.addAttribute("totalInventory", buchungsKalkulationService.getWertWarenbestand());
		model.addAttribute("calkSavesYear", buchungsKalkulationService.getKalkulatorischeEinsparungJahr());
		return "Sides/Home/index";
	}
	

}
