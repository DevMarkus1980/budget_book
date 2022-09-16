package de.struma.budget_book.Moduls.spar.controller;

import de.struma.budget_book.Service.AnzeigenService;
import de.struma.budget_book.Moduls.spar.service.BuchungsKalkulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

	AnzeigenService anzeigenService;
	BuchungsKalkulationService buchungsKalkulationService;

	public MainController(AnzeigenService anzeigenService, BuchungsKalkulationService buchungsKalkulationService) {
		this.anzeigenService = anzeigenService;
		this.buchungsKalkulationService = buchungsKalkulationService;
	}

	@GetMapping(value = {"/home", "/"})
	public String showHome(Model model) {
		buchungsKalkulationService.initCalkAllEntries();
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "Sides/Home/index";
	}
	

}
