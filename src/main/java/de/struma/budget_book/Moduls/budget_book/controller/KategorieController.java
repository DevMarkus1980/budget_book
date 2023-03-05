package de.struma.budget_book.Moduls.budget_book.controller;

import de.struma.budget_book.Service.AnzeigenService;
import de.struma.budget_book.Moduls.budget_book.service.KategorieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(value = {"/kategorie"})
@Controller
public class KategorieController {

    KategorieService kategorieService;
    AnzeigenService anzeigenService;

    public KategorieController(KategorieService kategorieService, AnzeigenService anzeigenService){
        this.kategorieService = kategorieService;
        this.anzeigenService = anzeigenService;
    }

    @GetMapping(value = {"/showAll"})
    public String showHome(Model model) {

        model.addAttribute("kategorien", kategorieService.getAllBuchung());
        model.addAttribute("displayModel", anzeigenService.updateDisplayView());
        return "Sides/Budget_Book/Kategorie/kategorie";
    }
    @GetMapping (value = "/showAll/delete/{id}")
    public String deleteKategorie(@PathVariable(name = "id") Long id){
        kategorieService.deleteBuchung(id);
        return "redirect:/kategorie/showAll/";
    }
}
