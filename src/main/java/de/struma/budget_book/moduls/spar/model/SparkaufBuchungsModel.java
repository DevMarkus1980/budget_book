package de.struma.budget_book.moduls.spar.model;

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
    Double kalkulatorischerJahresverbrauch;
    Double fehlendeMengeBisMHD;
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

    /*
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

    public String getUpdateDate() {
        String dateAsString = "";
        if (updateDate != null) {
            dateAsString = updateDate.toString();
        }
        return dateAsString;
    }

    public void setUpdateDate(String date) {
        if (!date.equals("")) {
            this.updateDate = LocalDate.parse(date);
        } else {
            this.updateDate = null;
        }
    }
    */
}
