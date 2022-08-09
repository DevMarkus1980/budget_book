package de.struma.budget_book.service;

import de.struma.budget_book.model.SparkaufBuchungsModel;
import de.struma.budget_book.repository.SparkaufBuchungsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class SparBuchungsService {

    private final SparkaufBuchungsRepository sparkaufBuchungsRepository;

    public SparBuchungsService(SparkaufBuchungsRepository sparkaufBuchungsRepository) {
        this.sparkaufBuchungsRepository = sparkaufBuchungsRepository;
    }

    public List<SparkaufBuchungsModel> getSparBuchung() {
        return sparkaufBuchungsRepository.findAll();
    }

    public SparkaufBuchungsModel createBuchung(SparkaufBuchungsModel sparInfo) {
        return sparkaufBuchungsRepository.save(sparInfo);
    }

    public void deleteBuchung(Long id) {
        if (sparkaufBuchungsRepository.existsById(id)) {
            sparkaufBuchungsRepository.deleteById(id);
        }

    }
}
