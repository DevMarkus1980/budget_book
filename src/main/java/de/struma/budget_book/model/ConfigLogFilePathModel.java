package de.struma.budget_book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ConfigLogFilePathModel {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;
	private String applicationName;
	private String path;
	private Boolean activeMonitoring;

}
