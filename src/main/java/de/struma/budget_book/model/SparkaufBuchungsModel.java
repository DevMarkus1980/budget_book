package de.struma.budget_book.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class SparkaufBuchungsModel {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="Spar_ID")
    private long id;

    private String produkt;
    private Double menge;
    private Timestamp buyDate;
    private Timestamp mHDDate;
    private Double einkaufsPreis;
    private Double normalPreis;

}
