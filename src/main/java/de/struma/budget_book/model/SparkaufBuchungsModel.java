package de.struma.budget_book.model;

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
    long id;

    String produkt;
    Double menge;
    Double mengeLager;
    Double kalkulatorischerJahresverbrauch;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate buyDate = LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate mHDDate = LocalDate.now().plusMonths(6);

    // Noch nicht sicher ob ich das brauche
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate emptyDate;

    Double einkaufsPreis;
    Double normalPreis;

    public void setMenge(Double menge) {
        this.menge = menge;
        this.mengeLager = menge;
    }

    public String getBuyDate() {
        String dateAsString = "";
        if (buyDate != null) {
            dateAsString = buyDate.toString();
        }
        return dateAsString;
    }

    public void setBuyDate(String date) {
        if (!date.equals("")) {
            this.buyDate = LocalDate.parse(date);
        } else {
            this.buyDate = null;
        }
    }
    public void setMHDDate(String date) {
        if (!date.equals("")) {
            this.mHDDate = LocalDate.parse(date);
        } else {
            this.mHDDate = null;
        }
    }

    public String getMHDDate() {
        String dateAsString = "";
        if (mHDDate != null) {
            dateAsString = mHDDate.toString();
        }
        return dateAsString;
    }

    public String getEmptyDate() {
        String dateAsString = "";
        if (emptyDate != null) {
            dateAsString = emptyDate.toString();
        }
        return dateAsString;
    }

    public void setEmptyDate(String date) {
        if (!date.equals("")) {
            this.emptyDate = LocalDate.parse(date);
        } else {
            this.emptyDate = null;
        }
    }

}
