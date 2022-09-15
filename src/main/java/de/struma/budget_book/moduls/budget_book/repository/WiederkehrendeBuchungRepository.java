package de.struma.budget_book.moduls.budget_book.repository;

import de.struma.budget_book.moduls.budget_book.model.buchung.WiederKehrendeBuchungModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface WiederkehrendeBuchungRepository extends JpaRepository<WiederKehrendeBuchungModel, Long> {
    List<WiederKehrendeBuchungModel> findByDatumErsteBuchung(@NonNull LocalDate datumErsteBuchung);
}
