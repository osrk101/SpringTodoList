package spring.todolist.controller;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@Autowired
	ModelMapper modelMapper;

	/** TodoListを全取得してviewTodoListへ送る */
	/** 検索ワードが入力されていれば検索してviewTodoListへ送る */
	@GetMapping("/viewTodoList")
	public String getTodoList(String searchWords, Model model) {
		List<Todo> todoList = null;
		if (searchWords == null) {
			todoList = todoService.getAllTodo();
		} else {
			todoList = todoService.getSearchTodo(searchWords);
		}
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
			return getAddTodo(todoForm, model);
		}
		todoService.addTodo(todoForm);
		return "redirect:/viewTodoList";
	}

	/** 指定されたTodoのIDを元にデータベースから1件取得する */
	@GetMapping("/updateTodo")
	public String getUpdateTodo(@RequestParam("id") @ModelAttribute("todoForm") Integer id, Model model) {
		List<User> assigneeList = userService.getUsersFullNameList();
		model.addAttribute("assigneeList", assigneeList);
		Todo todo = todoService.getTodoOne(id);
		TodoForm todoForm = modelMapper.map(todo, TodoForm.class);
		if (todoForm.getFinishedDate() != null) {
			todoForm.setStringFinished("1");
		}
		model.addAttribute("todoForm", todoForm);
		return "updateTodo";
	}

	/** Todoの編集をする　完了チェックもここで行う */
	@PostMapping("/updateTodo")
	public String postUpdateTodo(@Valid @ModelAttribute("todoForm") TodoForm todoForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			List<User> assigneeList = userService.getUsersFullNameList();
			model.addAttribute("assigneeList", assigneeList);
			return "updateTodo";
		}
		if ("1".equals(todoForm.getStringFinished()) && todoForm.getFinishedDate() == null) {
			todoForm.setFinishedDate(LocalDate.now());
		} else if (!"1".equals(todoForm.getStringFinished())) {
			todoForm.setFinishedDate(null);
		}
		todoService.updateTodo(todoForm);
		return "redirect:/viewTodoList";
	}

	/** 選択したTodoの削除確認 */
	@GetMapping("/confirmDelete")
	public String getConfirmDelete(@RequestParam("id") @ModelAttribute("todo") Integer id, Model model) {
		Todo todo = todoService.getTodoOne(id);
		model.addAttribute("todo", todo);
		return "confirmDelete";
	}

	/** Todoの削除 */
	@GetMapping("/deleteTodo")
	public String getDeleteTodo(@RequestParam Integer id) {
		todoService.deleteTodo(id);
		return "redirect:/viewTodoList";
	}

	@GetMapping("/finishedTodo")
	public String todoFinished(TodoForm todoForm) {
		todoForm.setFinishedDate(LocalDate.now());
		todoService.setFinishedDate(todoForm);
		return "redirect:/viewTodoList";
	}

}
