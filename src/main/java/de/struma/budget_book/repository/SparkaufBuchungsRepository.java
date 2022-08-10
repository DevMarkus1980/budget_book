package de.struma.budget_book.repository;

import de.struma.budget_book.model.SparkaufBuchungsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SparkaufBuchungsRepository extends JpaRepository<SparkaufBuchungsModel, Long> {

    List<SparkaufBuchungsModel> findByMengeLagerNotNull();
}
