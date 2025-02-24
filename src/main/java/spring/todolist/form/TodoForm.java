package spring.todolist.form;

import java.util.Date;

import lombok.Data;

@Data
public class TodoForm {

	private Integer id;
	
	private Integer userId;
	
	private String familyName;
	
	private String firstName;
	
	private String itemName;
	
	private Date registrationDate;
	
	private Date expireDate;

	private boolean isExpired;

	private Date finishedDate;

}
