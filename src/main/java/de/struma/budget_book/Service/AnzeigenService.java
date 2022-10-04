package de.struma.budget_book.Service;

import de.struma.budget_book.Model.DisplayModel;
import de.struma.budget_book.Moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.Moduls.budget_book.service.StatisticService;
import de.struma.budget_book.Moduls.spar.model.SparkaufBuchungsModel;
import de.struma.budget_book.Moduls.spar.service.BuchungsKalkulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Slf4j
@Service
public class AnzeigenService {

    StatisticService statisticService;
    BuchungsKalkulationService buchungsKalkulationService;

    public AnzeigenService(StatisticService statisticService, BuchungsKalkulationService buchungsKalkulationService){
        this.statisticService = statisticService;
        this.buchungsKalkulationService = buchungsKalkulationService;

    }
    public DisplayModel updateDisplayView(){
        DisplayModel updatedDisplay = new DisplayModel();
        updatedDisplay.setAktuelleEinsparungenLaufendesJahr(getAktuelleEinsparungen());
        updatedDisplay.setSaldo(getSaldo());
        updatedDisplay.setAktuellenWarenBestand(getAktuellenWarenbestand());
        updatedDisplay.setKalkulatorischeEinsparungenLaufendesJahr(getKalkulatorischeEinsparungenLaufendes());
        updatedDisplay.setTagesErsparnis(getTagesErsparnis());
        return updatedDisplay;
    }

    private double getTagesErsparnis() {

        DecimalFormat formatter = new DecimalFormat("#0.00");
        double temp =  0d;

        for(SparkaufBuchungsModel counter : buchungsKalkulationService.getKalkulatorischeEinsparungTag()){
            temp = temp+counter.getBestandsWert();
        }

        return temp;
    }

    public double getSaldo() {

        DecimalFormat formatter = new DecimalFormat("#0.00");
        double temp =  0d;
        for(BuchungModel counter : statisticService.findAllInThisMonth()){
            temp = temp+counter.getSumme();
        }
        return temp;
    }
    public double getAktuellenWarenbestand(){
        return buchungsKalkulationService.getWertWarenbestand();
    }
    public double getAktuelleEinsparungen(){
        return buchungsKalkulationService.getAlleEinsparungenInsgesamt();
    }
    public double getKalkulatorischeEinsparungenLaufendes(){
        return buchungsKalkulationService.getKalkulatorischeEinsparungJahr();
    }
}
