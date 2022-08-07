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
    private long id;

    private String produkt;
    private Double menge;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate buyDate = LocalDate.now();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate mHDDate;

    private Double einkaufsPreis;
    private Double normalPreis;

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
