package spring.todolist.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class TodoForm {

	private Integer id;

	@NotNull
	private Integer userId;

	@NotBlank
	@Size(max = 100)
	private String itemName; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expireDate;

	public boolean finished ;

}
