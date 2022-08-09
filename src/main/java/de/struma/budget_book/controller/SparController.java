package de.struma.budget_book.controller;

import de.struma.budget_book.model.SparkaufBuchungsModel;
import de.struma.budget_book.service.SparBuchungsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@RequestMapping(value = {"/spar/"})
@RestController
public class SparController {

	SparBuchungsService sparBuchungsService;
	public SparController(SparBuchungsService sparBuchungsService) {
		this.sparBuchungsService = sparBuchungsService;
	}

	@GetMapping
	public ModelAndView showHome(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("newEntry",  new SparkaufBuchungsModel());
		return new ModelAndView("Sides/Spar/spar_buchungen");
	}
	@PostMapping
	public ModelAndView saveHome(Model model, @ModelAttribute SparkaufBuchungsModel newEntry) {
		sparBuchungsService.createBuchung(newEntry);
		return new ModelAndView("redirect:/spar/");
	}

	@RequestMapping(value = "/delete/{id}")
	private ModelAndView deleteBuchung(@PathVariable(name = "id") Long id){
		sparBuchungsService.deleteBuchung(id);
		return new ModelAndView("redirect:/spar/");
	}


}


