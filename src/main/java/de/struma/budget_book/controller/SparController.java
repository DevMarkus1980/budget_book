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

	// Create
	@PostMapping
	public ModelAndView saveHome(Model model, @ModelAttribute SparkaufBuchungsModel newEntry) {

		sparBuchungsService.createBuchung(newEntry);
		return new ModelAndView("redirect:/spar/");
	}

	// Read
	@GetMapping
	public ModelAndView showHome(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "newEntry");
		model.addAttribute("newEntry",  new SparkaufBuchungsModel());
		return new ModelAndView("Sides/Spar/spar_buchungen");
	}

	@GetMapping(value = "/lager/")
	public ModelAndView showLager(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "lager");
		return new ModelAndView("Sides/Spar/spar_buchungen");
	}
	@GetMapping(value = "/inventur/")
	public ModelAndView showInventur(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "inventur");
		return new ModelAndView("Sides/Spar/spar_buchungen");
	}

	// Update

	// Delete
	@RequestMapping(value = "/delete/{id}")
	public ModelAndView deleteBuchung(@PathVariable(name = "id") Long id){
		sparBuchungsService.deleteBuchung(id);
		return new ModelAndView("redirect:/spar/");
	}


}


