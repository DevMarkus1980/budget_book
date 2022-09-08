package de.struma.budget_book.moduls.budget_book.model.buchung;

import de.struma.budget_book.moduls.budget_book.model.KategorieModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class BuchungModel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="buchung_id")
    Long id;
    String beschreibung = null;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate datum = LocalDate.now();
    Double summe = null;
    String kategorie = null;
    Boolean transaktion = false;

    public BuchungModel(String beschreibung, LocalDate datum, Double summe, String kategorie) {
        this.beschreibung = beschreibung;
        this.datum = datum;
        this.summe = summe;
        this.kategorie = kategorie;
    }

    @ManyToMany
    @JoinTable(name = "buchung_kategorie", joinColumns = @JoinColumn(name = "buchung_id", referencedColumnName = "buchung_id"),
            inverseJoinColumns = @JoinColumn(name = "kategorie_id", referencedColumnName = "kategorie_id"))
    private Collection<KategorieModel> kategorieModel;
}
