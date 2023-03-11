package de.struma.budget_book.Model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfigModel {


    // Ein Model um alle wichtigen Settings zu verwalten
    String whoCreatedFile;
    LocalDate lastValidateOfDate;
    boolean loggingEnabled;

    public ConfigModel(String creator) {
        whoCreatedFile = creator;
        lastValidateOfDate = LocalDate.now();
        loggingEnabled = true;
    }
    
}
