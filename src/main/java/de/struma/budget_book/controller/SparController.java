package de.struma.budget_book.controller;

import de.struma.budget_book.model.SparkaufBuchungsModel;
import de.struma.budget_book.service.SparBuchungsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class SparController {

	SparBuchungsService sparBuchungsService;
	public SparController(SparBuchungsService sparBuchungsService) {
		this.sparBuchungsService = sparBuchungsService;
	}

	// Create
	@PostMapping(value = {"/spar/"})
	public String saveHome(Model model, @ModelAttribute SparkaufBuchungsModel newEntry) {

		String redirect = "";
		if(newEntry.getId() == null)
			redirect = "redirect:/spar/";
		else
			redirect = "redirect:/spar/lager/";

		sparBuchungsService.createBuchung(newEntry);
		return redirect;
}

	// Read
	@GetMapping(value = {"/spar/"})
	public String showHome(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "newEntry");
		model.addAttribute("newEntry",  new SparkaufBuchungsModel());
		return "Sides/Spar/spar_buchungen";
	}

	@GetMapping(value = "/spar/lager/")
	public String showLager(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "lager");
		return "Sides/Spar/spar_buchungen";
	}
	@GetMapping(value = "/spar/inventur/")
	public String showInventur(Model model) {

		model.addAttribute("entries", sparBuchungsService.getInventar());
		model.addAttribute("view",  "inventur");
		return "Sides/Spar/spar_buchungen";
	}
	@GetMapping(value = "/spar/inventur/plus/{id}")
	public String plusProduct(Model model, @PathVariable(name = "id") Long id) {
		sparBuchungsService.plusProduct(id);
		model.addAttribute("entries", sparBuchungsService.getInventar());
		model.addAttribute("view",  "inventur");
		return "redirect:/spar/inventur/";
	}
	@GetMapping(value = "/spar/inventur/minus/{id}")
	public String minusProduct(Model model, @PathVariable(name = "id") Long id) {
		sparBuchungsService.minusProduct(id);
		model.addAttribute("entries", sparBuchungsService.getInventar());
		model.addAttribute("view",  "inventur");
		return "redirect:/spar/inventur/";
	}

	// Update

	@GetMapping("/spar/lager/edit/{id}")
	public String showUpdateForm(@PathVariable(name = "id") Long id,
								 Model model) {
		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "editEntry");
		model.addAttribute("newEntry",  sparBuchungsService.getSparbuchungByID(id));

		return "Sides/Spar/spar_buchungen";
	}

	// Delete
	@RequestMapping(value = "/spar/lager/delete/{id}")
	public String deleteBuchung(@PathVariable(name = "id") Long id){
		sparBuchungsService.deleteBuchung(id);
		return "redirect:/spar/lager/";
	}


}


