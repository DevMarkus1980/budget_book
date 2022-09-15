package de.struma.budget_book.moduls.budget_book.service;

import de.struma.budget_book.moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.moduls.budget_book.model.buchung.WiederKehrendeBuchungModel;
import de.struma.budget_book.moduls.budget_book.repository.WiederkehrendeBuchungRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.spi.LocaleServiceProvider;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class W_BuchungService {
    WiederkehrendeBuchungRepository  wiederkehrendeBuchungRepository;
    BuchungService buchungService;


    public W_BuchungService(WiederkehrendeBuchungRepository  wiederkehrendeBuchungRepository, BuchungService buchungService) {
        this.wiederkehrendeBuchungRepository = wiederkehrendeBuchungRepository;
        this.buchungService = buchungService;
    }

    // Create
    public WiederKehrendeBuchungModel createBuchung(WiederKehrendeBuchungModel sparInfo) {
        return wiederkehrendeBuchungRepository.save(sparInfo);
    }

    // Read
    public List<WiederKehrendeBuchungModel> getAllBuchung() {
        return wiederkehrendeBuchungRepository.findAll();
    }

    public WiederKehrendeBuchungModel getSparbuchungByID(Long id) {
        if(wiederkehrendeBuchungRepository.findById(id).isPresent())
           return  wiederkehrendeBuchungRepository.findById(id).get();
        log.error("Es konnte keine WiederkehrendeBuchung per ID gefunden werden");
        return new WiederKehrendeBuchungModel();
    }

    // Update

    // Delete
    public void deleteBuchung(Long id) {
        if (wiederkehrendeBuchungRepository.existsById(id)) {
            wiederkehrendeBuchungRepository.deleteById(id);
        }
    }

    public void bucheDailyWiederkehrendeBuchungModelToBuchungRepository(LocalDate today) {


        List<WiederKehrendeBuchungModel> set = wiederkehrendeBuchungRepository.findAll();

        if(!set.isEmpty()){
            for(WiederKehrendeBuchungModel e: set){
                if(checkIfBuchungHeuteGebucht(e, today)){
                    continue;
                }
                // TODO: Was soll er machen wenn mehrere Tage Fehlen
                else if( e.getDatumErsteBuchung().getDayOfMonth() == today.getDayOfMonth() &&
                        e.getDatumLetzteBuchung().isAfter(today.minusDays(1)) &&
                        e.getZuletztCronJobDurchgefuert().isBefore(today.minusDays(1L))){

                    long mengeAnTagenZwischenLetztenCronJobUndHeute= ChronoUnit.DAYS.between(
                            e.getZuletztCronJobDurchgefuert(),today);

                    for(int i = 1 ; i<=mengeAnTagenZwischenLetztenCronJobUndHeute; i++){

                        LocalDate setNewUpdateDate = e.getZuletztCronJobDurchgefuert().plusDays(1); // Er setzt bei jeder Inkremnentierung den Tag einen weiter
                        checkIfBuchungHeuteGebucht(e, setNewUpdateDate);
                        e.setZuletztCronJobDurchgefuert(setNewUpdateDate);
                        wiederkehrendeBuchungRepository.save(e);

                    }
                }
                e.setZuletztCronJobDurchgefuert(today);
                wiederkehrendeBuchungRepository.save(e);
            }
        }
    }

    private boolean checkIfBuchungHeuteGebucht(WiederKehrendeBuchungModel e, LocalDate today) {
        if(     e.getDatumErsteBuchung().getDayOfMonth() == today.getDayOfMonth() &&
                e.getDatumLetzteBuchung().isAfter(today.minusDays(1)) &&
                e.getZuletztCronJobDurchgefuert().equals(today.minusDays(1L))){

            BuchungModel createBuchung = createBuchungFromW_Buchung(e, today);

            if(e.getDatumErsteBuchung().getMonthValue() % 12 == 0 && e.getIntervalDerBuchung() == 12){
                buchungService.createBuchung(createBuchung);
                return true;
            }
            else if(e.getDatumErsteBuchung().getMonthValue() % 3 == 0 && e.getIntervalDerBuchung() == 3){
                buchungService.createBuchung(createBuchung);
                return true;
            }
            else if(e.getIntervalDerBuchung() == 1){
                buchungService.createBuchung(createBuchung);
                return true;
            }
        }
        return false;
    }

    private BuchungModel createBuchungFromW_Buchung(WiederKehrendeBuchungModel e, LocalDate today) {
        BuchungModel result = new BuchungModel();
        result.setBeschreibung(e.getBeschreibung());
        result.setDatum(today);
        result.setSumme(e.getSumme());
        result.setKategorie(e.getKategorie());

        return result;
    }
}
