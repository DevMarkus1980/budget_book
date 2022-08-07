package de.struma.budget_book.controller;

import de.struma.budget_book.model.SparkaufBuchungsModel;
import de.struma.budget_book.repository.SparkaufBuchungsRepository;
import de.struma.budget_book.service.SparBuchungsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping(value = {"/spar/"})
@RestController
public class SparController {

	Logger logger = LoggerFactory.getLogger(SparController.class);

	@Autowired
	SparkaufBuchungsRepository 	sparkaufBuchungsRepository;

	@Autowired
	SparBuchungsService sparBuchungsService;

	@GetMapping
	public ModelAndView showHome(Model model) {

		model.addAttribute("users", sparBuchungsService.getSparBuchung());
		model.addAttribute("newEntry",  new SparkaufBuchungsModel());
		return new ModelAndView("Sides/Spar/spar_buchungen");
	}
	@PostMapping
	public ModelAndView saveHome(Model model,
								 @ModelAttribute SparkaufBuchungsModel sparkaufBuchungsModel) {
		sparBuchungsService.createBuchung(sparkaufBuchungsModel);
		return new ModelAndView("redirect:/spar/");
	}

}


