package spring.todolist.form;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TodoForm {

	private Integer id;

	@NotNull
	private Integer userId;

	@NotBlank
	@Length(max = 100)
	private String itemName; 

	private LocalDate registrationDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expireDate;

	private LocalDate finishedDate;

	public boolean isFinished;

}
