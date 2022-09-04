package de.struma.budget_book.moduls.budget_book.model.buchung;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
public class WiederKehrendeBuchungModel {

    // Versuche das mal mit einer Vererbung zu lösen.
    // Dann kann ich die Klasse "WiederKehrendeBuchungModel" verwenden.
    // ohne das wirin der Primary Tabelle änderungen durchgeführt werden.

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="buchung_wiederkehrend_id")
    Long id;

    String beschreibung = null;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date datum = new Date(System.currentTimeMillis());
    Double summe = null;
    String kategorie = null;
    Boolean transaktion = true;

    Integer intervalDerBuchung;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date datumErsteBuchung = new Date(System.currentTimeMillis());

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date datumLetzteBuchung;


}
