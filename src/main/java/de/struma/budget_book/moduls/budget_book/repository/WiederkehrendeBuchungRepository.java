package de.struma.budget_book.moduls.budget_book.repository;

import de.struma.budget_book.moduls.budget_book.model.buchung.WiederKehrendeBuchungModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WiederkehrendeBuchungRepository extends JpaRepository<WiederKehrendeBuchungModel, Long> {
}
