package de.struma.budget_book.Moduls.budget_book.service;

import de.struma.budget_book.Moduls.budget_book.model.KategorieModel;
import de.struma.budget_book.Moduls.budget_book.repository.KategorieRepository;
import de.struma.budget_book.Service.FirstSetupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KategorieService {

    KategorieRepository kategorienRepository;
    FirstSetupService firstSetupService;

    public KategorieService(KategorieRepository kategorienRepository, FirstSetupService firstSetupService){
        this.kategorienRepository = kategorienRepository;
        this.firstSetupService = firstSetupService;

    }

    public Iterable<KategorieModel> getAllBuchung() {
        firstSetupService.initKategorie();
        return kategorienRepository.findAll();
    }

    public void deleteBuchung(Long id) {
        kategorienRepository.deleteById(id);
    }
}
