package de.struma.budget_book.Moduls.spar.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class SparkaufBuchungsModel {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="Spar_ID")
    Long id;

    String produkt;
    Double menge;
    Double mengeLager;
    Double kalkulatorischerJahresverbrauch = 0.0;
    Double fehlendeMengeBisMHD = 0.0;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate buyDate = LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate mHDDate = LocalDate.now().plusMonths(6);

    // Noch nicht sicher ob ich das brauche
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate updateDate;

    Double einkaufsPreis;
    Double normalPreis;

    public void setMenge(Double menge) {
        if(this.mengeLager == null) {
            this.mengeLager = menge;
            this.menge = menge;
        }
        else{
            this.mengeLager = menge;
        }
    }

}
