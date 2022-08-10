package de.struma.budget_book.service;

import de.struma.budget_book.model.SparkaufBuchungsModel;
import de.struma.budget_book.repository.SparkaufBuchungsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class SparBuchungsService {

    private final SparkaufBuchungsRepository sparkaufBuchungsRepository;

    public SparBuchungsService(SparkaufBuchungsRepository sparkaufBuchungsRepository) {
        this.sparkaufBuchungsRepository = sparkaufBuchungsRepository;
    }

    public List<SparkaufBuchungsModel> getSparBuchung() {
        return sparkaufBuchungsRepository.findAll();
    }

    public SparkaufBuchungsModel createBuchung(SparkaufBuchungsModel sparInfo) {
        return sparkaufBuchungsRepository.save(sparInfo);
    }

    public void deleteBuchung(Long id) {
        if (sparkaufBuchungsRepository.existsById(id)) {
            sparkaufBuchungsRepository.deleteById(id);
        }

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

    public List<SparkaufBuchungsModel> getInventar()  {return sparkaufBuchungsRepository.findAll();
    }

    public void plusProduct(Long id) {
        SparkaufBuchungsModel buchung = sparkaufBuchungsRepository.findById(id).get();
        if(buchung.getMengeLager() < buchung.getMenge()) {
            buchung.setMengeLager(buchung.getMengeLager() + 1);
            sparkaufBuchungsRepository.save(buchung);
        }
    }
    public void minusProduct(Long id) {
        SparkaufBuchungsModel buchung = sparkaufBuchungsRepository.findById(id).get();
        if(buchung.getMengeLager() > 0)  {
            buchung.setMengeLager(buchung.getMengeLager() - 1);
            sparkaufBuchungsRepository.save(buchung);
        }
    }
}
