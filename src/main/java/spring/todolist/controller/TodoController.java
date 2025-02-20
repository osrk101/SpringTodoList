package spring.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import spring.todolist.domain.user.model.Todo;
import spring.todolist.domain.user.model.User;
import spring.todolist.domain.user.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@GetMapping("/viewTodoList")
	public String getTodoList(HttpSession session, Model model) {
		List<Todo> todoList = todoService.getAllTodo();
		model.addAttribute("todoList", todoList);
		User loginUser = (User) session.getAttribute("loginUser");
		System.out.println("セッションから取得した loginUser: " + loginUser);
		model.addAttribute("loginUser", loginUser);
		return "viewTodoList";
	}
}
