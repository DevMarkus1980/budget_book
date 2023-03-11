package de.struma.budget_book.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;



// Beachten Sie, dass die Klasse des Objekts eine Standardkonstruktor und 
// Getter-Methoden benötigt, um von jackson serialisiert werden zu können.
public class JsonMapperService {


    // Methode um ein Object in einer Json Datei zu serialisieren   
    public static void serializeToJson(Object object, String filepath) {

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            mapper.writeValue(new File(filepath), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode um ein Object aus einer Json Datei zu deserialisieren und das abject zurück zu geben   
    public static <T> T deserializeFromJsonFile(String filename, Class<T> objectClass) {

        dateSetup();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Lese JSON aus der Datei
            File file = new File(filename);
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            // Deserialisiere JSON
            T object = objectMapper.readValue(json, objectClass);
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void dateSetup(){
        
    }
        
}
