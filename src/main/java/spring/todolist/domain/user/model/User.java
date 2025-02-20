package spring.todolist.domain.user.model;

import lombok.Data;

@Data
public class User {

	private int id;

	private String userName;

	private Integer pass;

	private String familyName;

	private String firstName;
}
