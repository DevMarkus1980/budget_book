package de.struma.budget_book.moduls.budget_book.controller;

import de.struma.budget_book.moduls.budget_book.service.KategorieService;
import de.struma.budget_book.moduls.budget_book.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(value = {"/kategorie"})
@Controller
public class KategorieController {

    KategorieService kategorieService;
    StatisticService statisticService;

    public KategorieController(KategorieService kategorieService, StatisticService statisticService){
        this.kategorieService = kategorieService;
        this.statisticService = statisticService;
    }

    @GetMapping(value = {"/showAll"})
    public String showHome(Model model) {

        model.addAttribute("kategorien", kategorieService.getAllBuchung());
        model.addAttribute("saldo", statisticService.getSaldo());
        return "Sides/Budget_Book/Kategorie/kategorie";
    }
}