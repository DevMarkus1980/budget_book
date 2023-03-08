package de.struma.budget_book.Moduls.budget_book.service;

import de.struma.budget_book.Config.FirstSetupConfig;
import de.struma.budget_book.Moduls.budget_book.model.KategorieModel;
import de.struma.budget_book.Moduls.budget_book.repository.KategorieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KategorieService {

    KategorieRepository kategorienRepository;
    FirstSetupConfig firstSetupService;

    public KategorieService(KategorieRepository kategorienRepository, FirstSetupConfig firstSetupService){
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
