package spring.todolist.domain.user.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Todo {

	private int id;

	private int userId;

	private String itemName;

	private LocalDate registrationDate;

	private LocalDate expireDate;

	public boolean isExpired() {
		if (finishedDate != null) {
			return false;
		}
		return expireDate.isBefore(LocalDate.now());
	}

	private LocalDate finishedDate;
	
	public boolean isFinished;

	private boolean isDeteled;
	
	private MUser assignee;

	
}
