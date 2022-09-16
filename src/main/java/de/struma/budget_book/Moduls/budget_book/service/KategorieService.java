package de.struma.budget_book.Moduls.budget_book.service;

import de.struma.budget_book.Moduls.budget_book.model.KategorieModel;
import de.struma.budget_book.Moduls.budget_book.repository.KategorieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KategorieService {

    KategorieRepository kategorienRepository;

    public KategorieService(KategorieRepository kategorienRepository){
        this.kategorienRepository = kategorienRepository;

    }

    public Iterable<KategorieModel> getAllBuchung() {
        String[] kategorienDummy = {
                "Haushalt", "Lebensmittel", "Auto", "Ausbildung", "Freizeit",
                "Hund","Kind","Kleidung", "FastFood", "BFW", "Ebay"};

        if(kategorienRepository.count() <1){
            kategorienRepository.deleteAll();
            for(int i = 0 ; i<10; i++){
                KategorieModel filler = new KategorieModel();
                filler.setName(kategorienDummy[i]);
                kategorienRepository.save(filler);

            }
        }

        return kategorienRepository.findAll();
    }
}
