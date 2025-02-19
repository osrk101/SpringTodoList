package spring.todolist.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserForm {

	@NotNull
	private int id;

	@NotBlank
	private String name;

	@NotBlank
	private String pass;

	private String familyName;

	private String firstName;
}