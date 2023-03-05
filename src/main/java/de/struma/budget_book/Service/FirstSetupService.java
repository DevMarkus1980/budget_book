package de.struma.budget_book.Service;

import de.struma.budget_book.Moduls.budget_book.model.KategorieModel;
import de.struma.budget_book.Moduls.budget_book.repository.KategorieRepository;
import org.springframework.stereotype.Service;

@Service
public class FirstSetupService {
    KategorieRepository kategorieRepository;

    public FirstSetupService(KategorieRepository _kategorieRepository){
        kategorieRepository = _kategorieRepository;
    }
    public void initKategorie() {
        if(kategorieRepository.count()<1){
            String[] kategorienDummy = {
                    "Haushalt", "Lebensmittel", "Auto", "Ausbildung", "Freizeit",
                    "Hund","Kind","Kleidung", "FastFood", "BFW", "Ebay"};

            if(kategorieRepository.count() <1){
                kategorieRepository.deleteAll();
                for(int i = 0 ; i<10; i++){
                    KategorieModel filler = new KategorieModel();
                    filler.setName(kategorienDummy[i]);
                    kategorieRepository.save(filler);
                }
            }
        }
    }
}
