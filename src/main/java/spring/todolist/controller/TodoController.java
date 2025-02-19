package spring.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import spring.todolist.domain.user.model.User;
import spring.todolist.domain.user.service.TodoService;
import spring.todolist.domain.user.service.UserService;

@Controller
public class TodoController {

	@Autowired
	UserService userService;

	@Autowired
	TodoService todoService;

	@GetMapping("/ViewTodoList")
	public String getTodoList(@ModelAttribute User loginUser, Model model) {

		return "ViewTodoList";
	}
}
