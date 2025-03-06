package spring.todolist.domain.user.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Todo {

	private int id;

	private int userId;
	
	private String familyName;

	private String firstName;
	
	private String itemName;
	
	@NotNull
	private LocalDate registrationDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expireDate;

	private boolean isExpired;

	private LocalDate finishedDate;
	
	private String stringFinished;

	private boolean isDeteled;
}
