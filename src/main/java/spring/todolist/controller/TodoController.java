package spring.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String getTodoList(Model model) {
		List<Todo> todoList = todoService.getAllTodo();
		model.addAttribute("todoList", todoList);
		return "viewTodoList";
	}

	/** 検索ワードでTodoを取り出す */
	@PostMapping("/viewTodoList")
	public String searchTodoList(String searchWords, Model model) {
		List<Todo> todoList = todoService.getSearchTodo(searchWords);
		model.addAttribute("todoList", todoList);
		return "viewTodoList";
	}

	/** Todo追加ページへ担当者リストを取得して表示させる */
	@GetMapping("/addTodo")
	public String getAddTodo(TodoForm todoForm, Model model) {
		List<User> assigneeList = userService.getUsersFullNameList();
		model.addAttribute("assigneeList", assigneeList);
		model.addAttribute(todoForm);
		return "addTodo";
	}

	/** 入力されたTodoのバリデーションチェックをしてデータベースに登録、その後リスト画面へ戻る */
	@PostMapping("/addTodo")
	public String postAddTodo(@Valid @ModelAttribute TodoForm todoForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
			return getAddTodo(todoForm, model);
		}
		todoService.addTodo(todoForm);
		return "redirect:/viewTodoList";
	}
	
	/** 指定されたTodoのIDを元にデータベースから1件取得する */
	@GetMapping("/update")
	public String getTodoOne(TodoForm todoForm, Model model) {
		List<User> assigneeList = userService.getUsersFullNameList();
		model.addAttribute("assigneeList", assigneeList);
		Todo todo = todoService.getTodoOne(todoForm.getId());
		System.out.println("todo:" + todo);
		model.addAttribute(todo);
		return "updateTodo";
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
