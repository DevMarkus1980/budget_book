package de.struma.budget_book.Service;

import de.struma.budget_book.moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.moduls.budget_book.repository.BuchungRepository;
import de.struma.budget_book.moduls.budget_book.service.BuchungService;
import de.struma.budget_book.moduls.budget_book.service.W_BuchungService;
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

    W_BuchungService w_buchungService;
    BuchungService buchungService;
    BuchungRepository buchungRepository;

    public CronJobService(BuchungService buchungService, BuchungRepository buchungRepository, W_BuchungService w_buchungService){
        this.buchungService = buchungService;
        this.buchungRepository = buchungRepository;
        this.w_buchungService = w_buchungService;
    }


    @Scheduled(cron = "0 0 8 * * *", zone = "GMT+2")
	//@Scheduled(cron = "3 * * * * *")
    public void testCronJob() throws NamingException {

        if (jobsEnabled) {
            w_buchungService.bucheDailyWiederkehrendeBuchungModelToBuchungRepository(LocalDate.now());

            //CreateDummyTestEntry();


        }
    }

    private void CreateDummyTestEntry() {
        BuchungModel testCronJob = new BuchungModel();
        testCronJob.setSumme(11.99D);
        testCronJob.setDatum(LocalDate.now());
        testCronJob.setBeschreibung(LocalDateTime.now()+" getestet für Cronjob in der Nacht");
        buchungService.createBuchung(testCronJob);
    }


}
