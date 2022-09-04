package de.struma.budget_book.moduls.budget_book.service;

import de.struma.budget_book.moduls.budget_book.model.buchung.WiederKehrendeBuchungModel;
import de.struma.budget_book.moduls.budget_book.repository.WiederkehrendeBuchungRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class W_BuchungService {
    WiederkehrendeBuchungRepository  wiederkehrendeBuchungRepository;

    public W_BuchungService(WiederkehrendeBuchungRepository  wiederkehrendeBuchungRepository) {
        this.wiederkehrendeBuchungRepository = wiederkehrendeBuchungRepository;
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
        WiederKehrendeBuchungModel buchung = new WiederKehrendeBuchungModel();
        if(wiederkehrendeBuchungRepository.findById(id).isPresent())
            buchung = wiederkehrendeBuchungRepository.findById(id).get();
        return buchung;

    }

    // Update

    // Delete
    public void deleteBuchung(Long id) {
        if (wiederkehrendeBuchungRepository.existsById(id)) {
            wiederkehrendeBuchungRepository.deleteById(id);
        }
    }

}
