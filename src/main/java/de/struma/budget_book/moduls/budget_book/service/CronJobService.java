package de.struma.budget_book.moduls.budget_book.service;

import de.struma.budget_book.moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.moduls.budget_book.repository.BuchungRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.naming.NamingException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EnableScheduling
@Configuration
public class CronJobService {
    @Value("${scheduled.jobs.enabled}")
    private boolean jobsEnabled;

    BuchungService buchungService;
    BuchungRepository buchungRepository;

    public CronJobService(BuchungService buchungService, BuchungRepository buchungRepository){
        this.buchungService = buchungService;
        this.buchungRepository = buchungRepository;
    }


    @Scheduled(cron = "0 0 0 * * *", zone = "GMT+2")
//	@Scheduled(cron = "1 * * * * *")
    public void testCronJob() throws NamingException {

        if (jobsEnabled) {
            BuchungModel testCronJob = new BuchungModel();
            testCronJob.setSumme(11.99D);
            testCronJob.setDatum(LocalDate.now());
            testCronJob.setBeschreibung(LocalDateTime.now()+" getestet für Cronjob in der Nacht");
            buchungService.createBuchung(testCronJob);


        }
    }


}
