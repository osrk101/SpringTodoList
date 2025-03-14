package spring.todolist.domain.user.model;

import lombok.Data;

public class User {
	@Data
	public class UserDetails {

		private int id;

		private String userName;

		private String pass;

		private String familyName;

		private String firstName;
	}
}