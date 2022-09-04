package de.struma.budget_book.moduls.budget_book.service;

import de.struma.budget_book.moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.moduls.budget_book.repository.BuchungRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class BuchungService {

    BuchungRepository buchungRepository;

    public BuchungService(BuchungRepository buchungRepository) {
        this.buchungRepository = buchungRepository;
    }

    // Create
    public BuchungModel createBuchung(BuchungModel sparInfo) {
        return buchungRepository.save(sparInfo);
    }
    
    // Read
    public Iterable<BuchungModel> getAllBuchung() {
        return buchungRepository.findAll();
    }

    public BuchungModel getSparbuchungByID(Long id) {
        BuchungModel buchung = new BuchungModel();
        if(buchungRepository.findById(id).isPresent())
            buchung = buchungRepository.findById(id).get();
        return buchung;

    }

    // Update

    // Delete
    public void deleteBuchung(Long id) {
        if (buchungRepository.existsById(id)) {
            buchungRepository.deleteById(id);
        }
    }


    public void setDummiesToTable() {

        String[] kategorien ={"Haushalt", "Auto", "Fastfood", "Wellness", "Lebensmittel"};
        List<BuchungModel> setDummies = new ArrayList<>();

        for(int i = 1 ; i<100; i++){
            Random r = new Random();
            setDummies.add(
                    new BuchungModel(
                            "Test-Objekt Nr."+i,
                            LocalDate.now().plusDays(r.nextInt(5*i))
                          , r.nextDouble() * 200 - 100,
                            kategorien[r.nextInt(5)]));
        }
        buchungRepository.saveAll(setDummies);
    }

}

