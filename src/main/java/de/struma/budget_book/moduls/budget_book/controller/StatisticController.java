package de.struma.budget_book.moduls.budget_book.controller;

import de.struma.budget_book.Service.AnzeigenService;
import de.struma.budget_book.moduls.budget_book.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(value = {"/statistik"})
@Controller
public class StatisticController {

    String statistikView = "Sides/Budget_Book/Statistik/statistik";
    AnzeigenService anzeigenService;
    StatisticService statisticService;

    public StatisticController(AnzeigenService anzeigenService, StatisticService statisticService){
        this.anzeigenService = anzeigenService;
        this.statisticService = statisticService;
    }

    @GetMapping(value = {"/showAll"})
    public String showHome(Model model) {
        model.addAttribute("all", statisticService.findAll());
        model.addAttribute("saldo", anzeigenService.getSaldo());
        return statistikView;
    }
    @GetMapping(value = {"/showThisMonth"})
    public String showThisMonth(Model model) {

        model.addAttribute("all", statisticService.findAllInThisMonth());
        model.addAttribute("saldo", anzeigenService.getSaldo());
        return statistikView;
    }
    @GetMapping(value = {"/showThisYear"})
    public String showThisYear(Model model) {

        model.addAttribute("all", statisticService.findAllInThisYear());
        model.addAttribute("saldo", anzeigenService.getSaldo());
        return statistikView;
    }

}
