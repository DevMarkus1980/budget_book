package de.struma.budget_book.Model;

import lombok.Data;

@Data
public class DisplayModel {
    double saldo;
    double aktuellenWarenBestand;
    double aktuelleEinsparungenLaufendesJahr;
    double kalkulatorischeEinsparungenLaufendesJahr;
    double tagesErsparnis;

}
