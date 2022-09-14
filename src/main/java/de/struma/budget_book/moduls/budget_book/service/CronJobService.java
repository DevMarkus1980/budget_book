package de.struma.budget_book.moduls.budget_book.service;

import de.struma.budget_book.moduls.budget_book.model.buchung.BuchungModel;
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

    public CronJobService(BuchungService buchungService){
        this.buchungService = buchungService;
    }


    @Scheduled(cron = "*/2 * * * *")
//	@Scheduled(cron = "1 * * * * *")
    public void testCronJob() throws NamingException {

        if (jobsEnabled) {
            BuchungModel testCronJob = new BuchungModel();
            testCronJob.setSumme(11.99D);
            testCronJob.setDatum(LocalDate.now());
            testCronJob.setBeschreibung(LocalDateTime.now()+" getestet");
            buchungService.createBuchung(testCronJob);


        }
    }


}
