package de.struma.budget_book.Moduls.spar.service;

import de.struma.budget_book.Moduls.spar.model.SparkaufBuchungsModel;
import de.struma.budget_book.Moduls.spar.repository.SparkaufBuchungsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class SparBuchungsService {

    SparkaufBuchungsRepository sparkaufBuchungsRepository;
    BuchungsKalkulationService buchungsKalkulationService;

    public SparBuchungsService(SparkaufBuchungsRepository sparkaufBuchungsRepository, BuchungsKalkulationService buchungsKalkulationService) {
        this.buchungsKalkulationService = buchungsKalkulationService;
        this.sparkaufBuchungsRepository = sparkaufBuchungsRepository;
    }

    public List<SparkaufBuchungsModel> getSparBuchung() {
        return sparkaufBuchungsRepository.findByMengeLagerGreaterThan(0.01);
    }

    public SparkaufBuchungsModel createBuchung(SparkaufBuchungsModel sparInfo) {
        //TODO: check warum keine o.o als neue lagerMenge gespeichert werden kann!
        setCalkYearConsum(sparInfo);
        setCalkMissingUntilYearOver(sparInfo);
        setBestandswert(sparInfo);
        setEingespartDurchDiesemEinkauf(sparInfo);
        return sparkaufBuchungsRepository.save(sparInfo);
    }

    public void deleteBuchung(Long id) {
        if (sparkaufBuchungsRepository.existsById(id)) {
            sparkaufBuchungsRepository.deleteById(id);
        }

    }
    public List<SparkaufBuchungsModel> getInventar()  {return sparkaufBuchungsRepository.findByMengeLagerGreaterThan(0.0);}

    public void plusProduct(Long id) {
        SparkaufBuchungsModel buchung = sparkaufBuchungsRepository.findById(id).get();
        if(buchung.getMengeLager() < buchung.getMenge()) {
            buchung.setMengeLager(buchung.getMengeLager() + 1);
            setCalkYearConsum(buchung);
            setCalkMissingUntilYearOver(buchung);
            sparkaufBuchungsRepository.save(buchung);
        }
    }
    public void minusProduct(Long id) {
        SparkaufBuchungsModel buchung = sparkaufBuchungsRepository.findById(id).get();
        if(buchung.getMengeLager() > 0)  {
            buchung.setMengeLager(buchung.getMengeLager() - 1);
            setCalkYearConsum(buchung);
            setCalkMissingUntilYearOver(buchung);
            sparkaufBuchungsRepository.save(buchung);
        }
    }

    public SparkaufBuchungsModel getSparbuchungByID(Long id) {
        SparkaufBuchungsModel buchung = sparkaufBuchungsRepository.findById(id).get();
        return buchung;
    }


    // Kalkulationen
    public void setCalkYearConsum(SparkaufBuchungsModel newEntry) {
        buchungsKalkulationService.setKalkulatorischeJahresMenge(newEntry);
    }
    public void setCalkMissingUntilYearOver(SparkaufBuchungsModel newEntry) {
        buchungsKalkulationService.setKalkulatorischeFehlendeMengeBisMHD(newEntry);
    }

    public void setBestandswert(SparkaufBuchungsModel newEntry) {
        buchungsKalkulationService.setBestandswert(newEntry);
    }

    public void setEingespartDurchDiesemEinkauf(SparkaufBuchungsModel newEntry) {
        buchungsKalkulationService.setEingespartDurchDiesemEinkauf(newEntry);
    }

    public List<SparkaufBuchungsModel> getSparBuchungWithNullInBestand() {
        return sparkaufBuchungsRepository.findByMengeLager(0.0);
    }
}
