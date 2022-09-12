package de.struma.budget_book.Service;

import de.struma.budget_book.moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.moduls.budget_book.service.StatisticService;
import de.struma.budget_book.moduls.spar.service.BuchungsKalkulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Slf4j
@Service
public class AnzeigenService {

    StatisticService statisticService;

    public AnzeigenService(StatisticService statisticService){
        this.statisticService = statisticService;

    }

    public String getSaldo() {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        double temp =  0d;
        for(BuchungModel counter : statisticService.findAllInThisMonth()){
            temp = temp+counter.getSumme();
        }
        ;
        return "Saldo: "+ formatter.format(temp) +"€";
    }
}
