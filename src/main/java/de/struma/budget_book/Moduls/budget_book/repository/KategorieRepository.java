package de.struma.budget_book.Moduls.budget_book.repository;


import de.struma.budget_book.Moduls.budget_book.model.KategorieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorieRepository extends JpaRepository<KategorieModel, Long> {


    boolean existsByName(String name);
}
