package de.struma.budget_book.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = {"/home", "/"})
@RestController
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);

	@GetMapping
	public ModelAndView showHome(Model model) {

		return new ModelAndView("Sides/Home/index");
	}
	

}
