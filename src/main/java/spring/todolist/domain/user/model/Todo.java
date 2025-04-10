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

	private LocalDate finishedDate;

	private boolean isDeteled;

	private MUser assignee;

	public boolean isExpired() {
		if (finishedDate != null) {
			return false;
		}
		return expireDate != null && expireDate.isBefore(LocalDate.now());
	}

	public boolean isFinished() {
		if (finishedDate != null) {
			return true;
		} else {
			return false;
		}
	}
}
