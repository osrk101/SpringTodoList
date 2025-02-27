package spring.todolist.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodoForm {

	private Integer id;

	@NotNull
	private Integer userId;

	private String familyName;

	private String firstName;

	@NotBlank
	private String itemName;

	private Date registrationDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expireDate;

	private boolean isExpired;

	private Date finishedDate;

	private boolean isDeteled;

}
