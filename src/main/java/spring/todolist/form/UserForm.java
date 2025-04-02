package spring.todolist.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserForm {

	private int id;

	@NotBlank
	private String userName;

	@NotBlank
	private String pass;
}