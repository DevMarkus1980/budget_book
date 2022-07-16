package de.struma.budget_book.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
public class LogFileModel {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="LOG_ID")
	private long id;
	private Timestamp dateTime;
	private String statusError;
	private String problemClass;
	private String activationProcess;
	private String application;
	private @Column(length = 2_500) String message;
	
	public LogFileModel() {}
}
