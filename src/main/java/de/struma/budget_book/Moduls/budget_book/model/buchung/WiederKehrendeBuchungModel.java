package de.struma.budget_book.Moduls.budget_book.model.buchung;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class WiederKehrendeBuchungModel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="buchung_wiederkehrend_id")
    Long id;

    String beschreibung = null;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate datum = LocalDate.now();
    Double summe = null;
    String kategorie = null;
    Boolean transaktion = true;
    LocalDate zuletztCronJobDurchgefuert = LocalDate.now().minusDays(10L); // minus 1 damit es am selben Tag noch ausgeführt werden kann zum testen

    Integer intervalDerBuchung;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate datumErsteBuchung = LocalDate.now();

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate datumLetzteBuchung = LocalDate.now();


}
