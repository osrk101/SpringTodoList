package spring.todolist.form;

import java.time.LocalDate;

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

	private LocalDate registrationDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expireDate;

	private boolean isExpired;

	private LocalDate finishedDate;

	private String stringFinished;

	public  boolean getFinished() {
		return finishedDate != null;
	}

	private boolean isDeteled;

}
