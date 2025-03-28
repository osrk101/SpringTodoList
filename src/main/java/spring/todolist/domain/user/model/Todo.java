package spring.todolist.domain.user.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Todo {

	private int id;

	private int userId;

	private String itemName;

	@NotNull
	private LocalDate registrationDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expireDate;

	public boolean isExpired() {
		if (finishedDate != null) {
			return false;
		}
		return expireDate.isBefore(LocalDate.now());
	}

	private LocalDate finishedDate;

	private boolean isDeteled;
	
	private MUser assignee;
}
