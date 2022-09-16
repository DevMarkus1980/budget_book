package de.struma.budget_book.Moduls.spar.service;

import de.struma.budget_book.Moduls.spar.model.SparkaufBuchungsModel;
import de.struma.budget_book.Moduls.spar.repository.SparkaufBuchungsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

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
        List<SparkaufBuchungsModel> alleEinsparungen = sparkaufBuchungsRepository.findByMengeLagerGreaterThan(0.01);
        for (SparkaufBuchungsModel einsparung : alleEinsparungen) {
            summe = summe + (einsparung.getNormalPreis() * einsparung.getMengeLager());
        }
        return summe;
    }

    public double getEinsparungAktuellesJahr() {
        double summe = 0.0;
        LocalDate firstDay = LocalDate.now().with(firstDayOfYear());
        List<SparkaufBuchungsModel> alleEinsparungen = sparkaufBuchungsRepository.findAll();
        for (SparkaufBuchungsModel einsparung : alleEinsparungen) {
            if(firstDay.isBefore(einsparung.getBuyDate()))
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

            if(fruesteBuchung.isAfter(einsparung.getBuyDate()))
                fruesteBuchung = einsparung.getBuyDate();
            if(firstDay.isBefore(einsparung.getBuyDate()))
                summe = summe + ((einsparung.getNormalPreis() - einsparung.getEinkaufsPreis())* einsparung.getMenge());
        }
        tageSeitErsteBuchung = ChronoUnit.DAYS.between(fruesteBuchung, LocalDate.now());
        tageBisEndeJahres = ChronoUnit.DAYS.between(LocalDate.now(), lastDay);
        double kalkBisEndeJahres = (summe / tageSeitErsteBuchung) * tageBisEndeJahres;

        return kalkBisEndeJahres + summe;
    }
    public void setKalkulatorischeJahresMenge(SparkaufBuchungsModel sparkaufBuchungsModel) {

        sparkaufBuchungsModel.setUpdateDate(LocalDate.now());
        double mengeDiff = sparkaufBuchungsModel.getMenge()-sparkaufBuchungsModel.getMengeLager();
        if(sparkaufBuchungsModel.getMengeLager()>0){
            long tageDesVerbrauchs = ChronoUnit.DAYS.between(
                    sparkaufBuchungsModel.getBuyDate(),
                    sparkaufBuchungsModel.getUpdateDate());
            if(mengeDiff < 1)
                sparkaufBuchungsModel.setKalkulatorischerJahresverbrauch(0.0);
            else
                sparkaufBuchungsModel.setKalkulatorischerJahresverbrauch((mengeDiff / tageDesVerbrauchs) * 360);
        }

    }
    public void setKalkulatorischeFehlendeMengeBisMHD(SparkaufBuchungsModel sparkaufBuchungsModel) {

        Double jahresverbrauch = sparkaufBuchungsModel.getKalkulatorischerJahresverbrauch();
        Double mengeTagesBedarf = jahresverbrauch /365;
        long tageDesVerbrauchs = ChronoUnit.DAYS.between(
                sparkaufBuchungsModel.getUpdateDate(),
                sparkaufBuchungsModel.getMHDDate());
        Double bedarfsMengeBisMHD = mengeTagesBedarf * tageDesVerbrauchs;
        Double fehlendeMenge = bedarfsMengeBisMHD - sparkaufBuchungsModel.getMengeLager();

        if(fehlendeMenge<0)
            fehlendeMenge = 0D;
        sparkaufBuchungsModel.setFehlendeMengeBisMHD(fehlendeMenge);

    }
    public void initCalkAllEntries(){
        List<SparkaufBuchungsModel> updateAll = sparkaufBuchungsRepository.findByMengeLagerGreaterThan(0.01);

        for(SparkaufBuchungsModel entry: updateAll){
            if(entry.getUpdateDate() == null){
                entry.setUpdateDate(LocalDate.now());
            }

            if(!Objects.equals(entry.getMenge(), entry.getMengeLager())){
                setKalkulatorischeJahresMenge(entry);
                setKalkulatorischeFehlendeMengeBisMHD(entry);
            }
        }
        sparkaufBuchungsRepository.saveAll(updateAll);

    }

}
