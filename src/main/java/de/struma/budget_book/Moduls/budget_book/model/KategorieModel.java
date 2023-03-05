package de.struma.budget_book.Moduls.budget_book.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class KategorieModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="kategorie_id")
    Long id;
    String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date erstellDatum = new Date(System.currentTimeMillis());

}
