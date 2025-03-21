package spring.todolist.domain.user.model;

import lombok.Data;

@Data
public class MUser {

	private int id;

	private String username;

	private String password;

	private String familyName;

	private String firstName;
}