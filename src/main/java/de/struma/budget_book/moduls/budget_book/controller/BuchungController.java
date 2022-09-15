package de.struma.budget_book.moduls.budget_book.controller;

import de.struma.budget_book.Service.AnzeigenService;
import de.struma.budget_book.moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.moduls.budget_book.model.buchung.WiederKehrendeBuchungModel;
import de.struma.budget_book.moduls.budget_book.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(value = {"/buchung"})
@Controller
public class BuchungController {

    BuchungService buchungService;
    AnzeigenService anzeigenService;
    W_BuchungService w_buchungService;
    KategorieService kategorieService;
    public BuchungController(BuchungService buchungService, W_BuchungService w_buchungService, AnzeigenService anzeigenService, KategorieService kategorieService){
        this.buchungService = buchungService;
        this.w_buchungService = w_buchungService;
        this.anzeigenService = anzeigenService;
        this.kategorieService = kategorieService;
    }

    // Neue Buchungen
    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("neueBuchung", new BuchungModel());
        model.addAttribute("kategorien", kategorieService.getAllBuchung());
        model.addAttribute("displayModel", anzeigenService.updateDisplayView());
        return "Sides/Budget_Book/Buchung/neue_buchung";
    }

    @PostMapping(value = {"/neue"})
    public String saveModel(Model model, @ModelAttribute BuchungModel neueBuchung) {

        if(!neueBuchung.getTransaktion()) {
            neueBuchung.setSumme(-neueBuchung.getSumme());
        }
        buchungService.createBuchung(neueBuchung);
        model.addAttribute("displayModel", anzeigenService.updateDisplayView());
        return "redirect:/buchung";
    }

    // Wiederkehrende Buchungen
    @GetMapping(value = {"/neueWiederBuchung"})
    public String showWiederKehrendeHome(Model model) {
        model.addAttribute("neueBuchung", new WiederKehrendeBuchungModel());
        model.addAttribute("all", (w_buchungService.getAllBuchung()));
        model.addAttribute("displayModel", anzeigenService.updateDisplayView());

        return "Sides/Budget_Book/Buchung/w_buchung";
    }

    @PostMapping(value = {"/neueWiederBuchung"})
    public String saveModel(Model model, @ModelAttribute WiederKehrendeBuchungModel neueBuchung) {

        if(!neueBuchung.getTransaktion()) {
            neueBuchung.setSumme(-neueBuchung.getSumme());
        }
        w_buchungService.createBuchung(neueBuchung);
        model.addAttribute("displayModel", anzeigenService.updateDisplayView());
        return "redirect:/buchung/neueWiederBuchung";
    }

}
