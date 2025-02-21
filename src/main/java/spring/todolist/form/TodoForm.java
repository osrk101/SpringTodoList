package spring.todolist.form;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodoForm {

	@NotNull
	private Integer id;
	
	@NotNull
	private Integer userId;
	
	@NotBlank
	private String familyName;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String itemName;
	
	@NotNull
	private Date registrationDate;
	
	@NotNull
	private Date expireDate;

	private boolean isExpired;

	@NotNull
	private Date finishedDate;

}
