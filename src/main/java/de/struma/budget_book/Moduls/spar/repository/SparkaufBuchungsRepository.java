package de.struma.budget_book.Moduls.spar.repository;

import de.struma.budget_book.Moduls.spar.model.SparkaufBuchungsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SparkaufBuchungsRepository extends JpaRepository<SparkaufBuchungsModel, Long> {


    List<SparkaufBuchungsModel> findByMengeLagerGreaterThan(Double mengeLager);


}
