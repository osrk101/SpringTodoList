package spring.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import spring.todolist.domain.user.model.Todo;
import spring.todolist.domain.user.model.User;
import spring.todolist.domain.user.service.TodoService;
import spring.todolist.domain.user.service.UserService;
import spring.todolist.form.TodoForm;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@Autowired
	UserService userService;

	/** TodoListを全取得してviewTodoListへ送る */
	@GetMapping("/viewTodoList")
	public String getTodoList(HttpSession session, Model model) {
		List<Todo> todoList = todoService.getAllTodo();
		model.addAttribute("todoList", todoList);
		/** ログインユーザー情報をセッションへ入れる */
		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("loginUser", loginUser);
		return "viewTodoList";
	}

	/** 検索ワードでTodoを取り出す */
	@PostMapping("/viewTodoList")
	public String searchTodoList(String searchWords, HttpSession session, Model model) {
		List<Todo> todoList = todoService.getSearchTodo(searchWords);
		model.addAttribute("todoList", todoList);
		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("loginUser", loginUser);
		return "viewTodoList";
	}

	@GetMapping("/addTodo")
	public String getAdd( @ModelAttribute TodoForm todoForm, Model model) {
		List<User> assigneeList = userService.getUsersFullNameList();
		model.addAttribute("assigneeList", assigneeList);
		return "addTodo";
	}

	@PostMapping("/addTodo")
	public String addTodo(@Valid @ModelAttribute TodoForm todoForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return getAdd(todoForm, model);
		}
		todoService.addTodo(todoForm);
		return "redirect:viewTodoList";
	}

	@PostMapping("/update")
	public String updateTodo() {
		return null;

	}

	@PostMapping("/finished")
	public void todoFinished(Model model) {
		todoService.setFinishedDate();
	}
}
