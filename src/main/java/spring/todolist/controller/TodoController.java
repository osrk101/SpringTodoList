package spring.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import spring.todolist.domain.user.model.Todo;
import spring.todolist.domain.user.model.User;
import spring.todolist.domain.user.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	/** TodoListを全取得してviewTodoListへ送る */
	@GetMapping("/viewTodoList")
	public String getTodoList(HttpSession session, Model model) {
		List<Todo> todoList = todoService.getAllTodo();
		model.addAttribute("todoList", todoList);
		/** ログインユーザー情報をセッションへ入れる */
		User loginUser = (User) session.getAttribute("loginUser");
		System.out.println("セッションから取得した loginUser: " + loginUser);
		model.addAttribute("loginUser", loginUser);
		return "viewTodoList";
	}

	/** 検索ワードから作業を探して取り出す */
	@PostMapping("/viewTodoList")
	public List<Todo> seachTodoList() {
		System.out.println(searchWords);
		List<Todo> seachTodoList = todoService.getSearchTodoList(searchWords);
		return seachTodoList;

	}

	@PostMapping("/add")
	public String addTodo() {
		return null;
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
