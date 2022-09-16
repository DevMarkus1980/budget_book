package de.struma.budget_book.Moduls.spar.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UtilityController {
	
	@Value("${app.version}")
	private String appVersion;
	
	@Bean(name = "versionsnummer") // Gibt einen String zum Frontend über das Interface FrontService
	public FrontService getVersion() {
		return () -> "v"+appVersion;
	}

	@Bean(name = "app_name") // Gibt einen String zum Frontend über das Interface FrontService
	public FrontService getAppName() {
		return () -> "<< BudgetBook "+"v"+appVersion +" >>";
	}
}
interface FrontService{
	String getVersion();
}