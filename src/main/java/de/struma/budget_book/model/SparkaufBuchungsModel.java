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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate buyDate = LocalDate.now();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate mHDDate = LocalDate.now().plusWeeks(2);

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

}
