package de.struma.budget_book.Config;

import java.io.File;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import de.struma.budget_book.Model.ConfigModel;
import de.struma.budget_book.Service.JsonMapperService;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

      JsonMapperService.serializeToJson(
        new ConfigModel("autoConfigFil"),
        System.getProperty("user.dir")+File.separator+"config.json");
      
    return;
    }
}
