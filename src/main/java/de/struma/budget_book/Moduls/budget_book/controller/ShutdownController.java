package de.struma.budget_book.Moduls.budget_book.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShutdownController {

    private final ApplicationContext appContext;

    public ShutdownController(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @Async
    @GetMapping("/shutdown/")
    public String shutdown() throws InterruptedException {
        new Thread(() -> {
            try {
                ShutDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return "Sides/Home/say_goog_bye";
    }


    private void ShutDown() throws InterruptedException {

        Thread.sleep(5000);
        SpringApplication.exit(appContext, () -> 0);
    }

}