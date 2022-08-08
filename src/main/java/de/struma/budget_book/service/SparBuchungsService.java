package de.struma.budget_book.service;

import de.struma.budget_book.model.SparkaufBuchungsModel;
import de.struma.budget_book.repository.SparkaufBuchungsRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class SparBuchungsService {

    private final SparkaufBuchungsRepository sparkaufBuchungsRepository;

    public SparBuchungsService(SparkaufBuchungsRepository sparkaufBuchungsRepository) {
        this.sparkaufBuchungsRepository = sparkaufBuchungsRepository;
    }

    public List<SparkaufBuchungsModel> getSparBuchung() {
        return sparkaufBuchungsRepository.findAll();
    }

    public SparkaufBuchungsModel createBuchung(SparkaufBuchungsModel SparInfo) {
        return sparkaufBuchungsRepository.save(SparInfo);
    }

    public void deleteBuchung(Long id) {
        if (sparkaufBuchungsRepository.existsById(id)) {
            sparkaufBuchungsRepository.deleteById(id);
        }

    }
}
