package de.struma.budget_book.Moduls.budget_book.repository;


import de.struma.budget_book.Moduls.budget_book.model.buchung.BuchungModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface BuchungRepository extends JpaRepository<BuchungModel, Long> {


    List<BuchungModel> findByDatumBetween(@NonNull LocalDate datumStart, @NonNull LocalDate datumEnd);


}
