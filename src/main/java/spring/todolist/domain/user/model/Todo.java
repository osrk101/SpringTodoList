package spring.todolist.domain.user.model;

import java.util.Date;

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
	
	private Date registrationDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expireDate;

	private boolean isExpired;

	private Date finishedDate;

	private boolean isDeteled;
}
