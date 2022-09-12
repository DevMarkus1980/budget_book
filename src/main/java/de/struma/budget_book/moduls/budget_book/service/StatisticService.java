package de.struma.budget_book.moduls.budget_book.service;

import de.struma.budget_book.moduls.budget_book.model.buchung.BuchungModel;
import de.struma.budget_book.moduls.budget_book.repository.BuchungRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class StatisticService {

    BuchungRepository buchungRepository;

    public StatisticService(BuchungRepository buchungRepository){
        this.buchungRepository = buchungRepository;
    }
    public Iterable<BuchungModel> findAll() {
        return buchungRepository.findAll();
    }

    public List<BuchungModel> findAllInThisMonth() {

        return buchungRepository.findByDatumBetween(LocalDate.now()
                .with( TemporalAdjusters.firstDayOfMonth() ),
                LocalDate.now().with( TemporalAdjusters.lastDayOfMonth()));
    }

    public List<BuchungModel> findAllInThisYear() {
        return buchungRepository.findByDatumBetween(LocalDate.now()
                        .with( TemporalAdjusters.firstDayOfYear() ),
                LocalDate.now().with( TemporalAdjusters.lastDayOfYear()));

    }

}
