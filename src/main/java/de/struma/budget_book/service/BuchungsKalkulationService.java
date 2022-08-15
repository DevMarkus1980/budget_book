package de.struma.budget_book.service;

import de.struma.budget_book.model.SparkaufBuchungsModel;
import de.struma.budget_book.repository.SparkaufBuchungsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
public class BuchungsKalkulationService {

    SparkaufBuchungsRepository sparkaufBuchungsRepository;

    public BuchungsKalkulationService(SparkaufBuchungsRepository sparkaufBuchungsRepository){
        this.sparkaufBuchungsRepository = sparkaufBuchungsRepository;
    }

    public double getAlleEinsparungenInsgesamt() {

        double summe = 0.0;
        List<SparkaufBuchungsModel> alleEinsparungen = sparkaufBuchungsRepository.findAll();
        for (SparkaufBuchungsModel einsparung : alleEinsparungen) {
            summe = summe + ((einsparung.getNormalPreis() - einsparung.getEinkaufsPreis())* einsparung.getMenge());
        }
        return summe;
    }

    public double getWertWarenbestand() {
        double summe = 0.0;
        List<SparkaufBuchungsModel> alleEinsparungen = sparkaufBuchungsRepository.findByMengeLagerNotNull();
        for (SparkaufBuchungsModel einsparung : alleEinsparungen) {
            summe = summe + (einsparung.getNormalPreis() * einsparung.getMengeLager());
        }
        return summe;
    }

    public double getEinsparungAktuellesJahr() {  // Noch nicht implementiert
        double summe = 0.0;
        LocalDate firstDay = LocalDate.now().with(firstDayOfYear());
        List<SparkaufBuchungsModel> alleEinsparungen = sparkaufBuchungsRepository.findByMengeLagerNotNull();
        for (SparkaufBuchungsModel einsparung : alleEinsparungen) {
            if(firstDay.isBefore(LocalDate.parse(einsparung.getBuyDate())))
                summe = summe + ((einsparung.getNormalPreis() - einsparung.getEinkaufsPreis())* einsparung.getMenge());

        }
        return summe;
    }


    public double getKalkulatorischeEinsparungJahr() {
        double summe = 0.0;
        long tageSeitErsteBuchung = 0;
        long tageBisEndeJahres = 0;

        LocalDate fruesteBuchung = LocalDate.now();
        LocalDate lastDay = LocalDate.now().with(lastDayOfYear());
        LocalDate firstDay = LocalDate.now().with(firstDayOfYear());

        List<SparkaufBuchungsModel> alleEinsparungen = sparkaufBuchungsRepository.findAll();
        for (SparkaufBuchungsModel einsparung : alleEinsparungen) {

            if(fruesteBuchung.isAfter(LocalDate.parse(einsparung.getBuyDate())))
                fruesteBuchung = LocalDate.parse(einsparung.getBuyDate());
            if(firstDay.isBefore(LocalDate.parse(einsparung.getBuyDate())))
                summe = summe + ((einsparung.getNormalPreis() - einsparung.getEinkaufsPreis())* einsparung.getMenge());
        }
        tageSeitErsteBuchung = ChronoUnit.DAYS.between(fruesteBuchung, LocalDate.now());
        tageBisEndeJahres = ChronoUnit.DAYS.between(LocalDate.now(), lastDay);
        double kalkBisEndeJahres = (summe / tageSeitErsteBuchung) * tageBisEndeJahres;

        return kalkBisEndeJahres + summe;
    }
    public Double getKalkulatorischeJahresMenge(SparkaufBuchungsModel sparkaufBuchungsModel) {

        sparkaufBuchungsModel.setUpdateDate(String.valueOf(LocalDate.now().toString()));
        double mengeDiff = sparkaufBuchungsModel.getMenge()-sparkaufBuchungsModel.getMengeLager();
        long tageDesVerbrauchs = ChronoUnit.DAYS.between(
                                                    LocalDate.parse(sparkaufBuchungsModel.getBuyDate()),
                                                    LocalDate.parse(sparkaufBuchungsModel.getUpdateDate()));
        if(mengeDiff < 1)
            return 0.0;

        return (mengeDiff / tageDesVerbrauchs) * 360;

    }
}
