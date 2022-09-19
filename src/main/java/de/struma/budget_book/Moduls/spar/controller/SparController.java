package de.struma.budget_book.Moduls.spar.controller;

import de.struma.budget_book.Moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.Moduls.spar.model.SparkaufBuchungsListDTO;
import de.struma.budget_book.Service.AnzeigenService;
import de.struma.budget_book.Moduls.spar.model.SparkaufBuchungsModel;
import de.struma.budget_book.Moduls.spar.service.SparBuchungsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class SparController {

	SparBuchungsService sparBuchungsService;
	AnzeigenService anzeigenService;
	public SparController(SparBuchungsService sparBuchungsService, AnzeigenService anzeigenService) {
		this.sparBuchungsService = sparBuchungsService;
		this.anzeigenService = anzeigenService;
	}

	// Create
	@PostMapping(value = {"/spar/"})
	public String saveHome(Model model,
						   @ModelAttribute SparkaufBuchungsModel newEntry) {

		//TODO: Diese Methode muss sauber in den Service verlagert werden
		String redirect = "";
		if(newEntry.getId() == null)
			redirect = "redirect:/spar/";
		else{
			redirect = "redirect:/spar/lager/";
		}

		sparBuchungsService.createBuchung(newEntry);
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return redirect;
}

	// Read
	@GetMapping(value = {"/spar/"})
	public String showHome(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "newEntry");
		model.addAttribute("newEntry",  new SparkaufBuchungsModel());
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "Sides/Spar/spar_buchungen";
	}

	@GetMapping(value = "/spar/lager/")
	public String showLager(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "lager");
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "Sides/Spar/spar_buchungen";
	}
	@GetMapping(value = "/spar/archiv/")
	public String showLagerArchiv(Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchungWithNullInBestand());
		model.addAttribute("view",  "lager");
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "Sides/Spar/spar_buchungen";
	}

	@GetMapping(value = "/spar/inventur/")
	public String showInventur(Model model) {

		SparkaufBuchungsListDTO setDTO = new SparkaufBuchungsListDTO();
		sparBuchungsService.getInventar().iterator().forEachRemaining(setDTO ::addBuchung);
		model.addAttribute("form", setDTO);
		model.addAttribute("path","/spar/inventur/");

		model.addAttribute("view",  "inventur");
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "Sides/Spar/inventur";
	}
	@PostMapping(value = {"/spar/inventur/"})
	public String saveInventur(Model model,
							   @ModelAttribute SparkaufBuchungsListDTO form) {

		sparBuchungsService.saveAllAfterInventur(form.getSetDTO());


		model.addAttribute("view",  "inventur");
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "redirect:/spar/inventur/";

	}

	// Update

	@GetMapping("/spar/lager/edit/{id}")
	public String showUpdateForm(@PathVariable(name = "id") Long id,
								 Model model) {
		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "editEntry");
		model.addAttribute("newEntry",  sparBuchungsService.getSparbuchungByID(id));
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "Sides/Spar/spar_buchungen";
	}
	@GetMapping("/spar/lager/calc/{id}")
	public String calcVolumeToBuy(@PathVariable(name = "id") Long id,
								 Model model) {

		model.addAttribute("entries", sparBuchungsService.getSparBuchung());
		model.addAttribute("view",  "editEntry");
		model.addAttribute("newEntry",  sparBuchungsService.getSparbuchungByID(id));
		model.addAttribute("displayModel", anzeigenService.updateDisplayView());
		return "Sides/Spar/calc";
	}

	// Delete
	@RequestMapping(value = "/spar/lager/delete/{id}")
	public String deleteBuchung(@PathVariable(name = "id") Long id){
		sparBuchungsService.deleteBuchung(id);
		return "redirect:/spar/lager/";
	}


}


