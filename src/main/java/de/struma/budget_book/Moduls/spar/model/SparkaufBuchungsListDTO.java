package de.struma.budget_book.Moduls.spar.model;

import de.struma.budget_book.Moduls.budget_book.model.buchung.BuchungModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SparkaufBuchungsListDTO {
    private List<SparkaufBuchungsModel> setDTO;

    public SparkaufBuchungsListDTO(){
        setDTO = new ArrayList<>();
    }

    public void addBuchung(SparkaufBuchungsModel logFileModel) {

        this.setDTO.add(logFileModel);
    }


}
