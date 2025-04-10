package spring.todolist.controller;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import spring.todolist.domain.user.model.MUser;
import spring.todolist.domain.user.model.Todo;
import spring.todolist.domain.user.service.TodoService;
import spring.todolist.domain.user.service.UserService;
import spring.todolist.form.TodoForm;

@Controller
@Slf4j
public class TodoController {

	@Autowired
	TodoService todoService;

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;

	/* 表示するページのリクエストパスを取得*/
	@ModelAttribute("currentPath")
	public String setCurrentPath(HttpServletRequest request) {
		return request.getRequestURI();
	}
	/* 完了チェックが入っていれば完了日を設定する 
	 * 入っていなければ完了日をクリアする*/
	private void finishedControl(Todo todo, TodoForm form) {
		if (form.isFinished()) {
			if (todo.getFinishedDate() == null) {
				todo.setFinishedDate(LocalDate.now());
			}
		} else {
			todo.setFinishedDate(null);
		}
	}

	/** TodoListを全取得してviewTodoListへ送る */
	/** 検索ワードが入力されていれば検索してviewTodoListへ送る */
	@GetMapping("/viewTodoList")
	public String getTodoList(String searchWords, Model model) {
		log.info("Todoリスト一覧を表示します。検索ワード={}", searchWords);
		List<Todo> todoList = null;
		if (searchWords == null) {
			log.debug("すべてのTodoを取得します。");
			todoList = todoService.getAllTodo();
		} else {
			log.debug("検索条件を用いたTodo検索をします。：{}", searchWords);
			todoList = todoService.getSearchTodo(searchWords);
		}
		model.addAttribute("todoList", todoList);
		model.addAttribute("searchWords", searchWords);
		return "viewTodoList";
	}

	/** Todo追加ページへ担当者リストを取得して表示させる */
	@GetMapping("/addTodo")
	public String getAddTodo(@ModelAttribute TodoForm todoForm, Model model) {
		log.info("Todo追加画面を表示します。");
		List<MUser> assigneeList = userService.getUsersFullNameList();
		model.addAttribute("assigneeList", assigneeList);
		return "addTodo";
	}

	/** 入力されたTodoのバリデーションチェックをしてデータベースに追加、その後リスト画面へ戻る */
	@PostMapping("/addTodo")
	public String postAddTodo(@Validated @ModelAttribute TodoForm todoForm, BindingResult bindingResult, Model model) {
		log.info("Todo追加作業を開始します。入力内容={}", todoForm);
		if (bindingResult.hasErrors()) {
			log.warn("バリデーションエラーが発生しました。：{}", bindingResult.getAllErrors());
			return getAddTodo(todoForm, model);
		}
		Todo todo = modelMapper.map(todoForm, Todo.class);
		finishedControl(todo, todoForm);
		todoService.addTodo(todo);
		log.info("新規Todoを追加しました。");
		return "redirect:/viewTodoList";
	}

	/** 指定されたTodoのIDを元にデータベースから1件取得する */
	@GetMapping("/updateTodo")
	public String getUpdateTodo(@RequestParam("id") Integer id, @ModelAttribute TodoForm todoForm,
			BindingResult bindingResult, Model model)
			throws Exception {
		log.info("Todo更新画面を表示します");
		if (!bindingResult.hasErrors()) {
			Todo todo = todoService.getTodoOne(id);
			log.info("取得されたTodo = {}", todo);
			if (todo == null) {
				throw new Exception("指定されたTodo（ID: " + id + "）は存在しません。");
			}
			modelMapper.map(todo, todoForm);
		}
		List<MUser> assigneeList = userService.getUsersFullNameList();
		model.addAttribute("assigneeList", assigneeList);
		log.info("取得されたTodoForm = {}", todoForm);
		return "updateTodo";
	}

	/** Todoの更新をする */
	@PostMapping("/updateTodo")
	public String postUpdateTodo(@ModelAttribute @Validated TodoForm todoForm, BindingResult bindingResult, Model model)
			throws Exception {
		log.info("Todo更新作業を開始します。更新対象ID={} 入力内容={}", todoForm.getId(), todoForm);
		if (bindingResult.hasErrors()) {
			log.warn("バリデーションエラーが発生しました。：{}", bindingResult.getAllErrors());
			return getUpdateTodo(todoForm.getId(), todoForm, bindingResult, model);
		}
		Todo todo = modelMapper.map(todoForm, Todo.class);
		finishedControl(todo, todoForm);
		todoService.updateTodo(todo);
		log.info("Todoを更新しました。ID={}", todoForm.getId());
		return "redirect:/viewTodoList";
	}

	/** 選択したTodoの削除確認 */
	@GetMapping("/confirmDelete")
	public String getConfirmDelete(@RequestParam("id") Integer id, Model model) throws Exception {
		log.info("Todo削除確認画面を表示します。");
		Todo todo = todoService.getTodoOneWithAssignee(id);
		log.info("Todoを取得。{}", todo);
		if (todo == null) {
			throw new Exception("指定されたTodo（ID: " + id + "）は存在しません。");
		}
		model.addAttribute("todo", todo);
		return "confirmDelete";
	}

	/** Todoの削除 */
	@PostMapping("/deleteTodo")
	public String postDeleteTodo(@RequestParam Integer id) {
		log.info("Todo削除作業を開始します。");
		todoService.deleteTodo(id);
		return "redirect:/viewTodoList";
	}

	@GetMapping("/finishedTodo")
	public String todoFinished(@RequestParam Integer id) {
		log.info("Todo完了作業を開始します。");
		Todo todo = todoService.getTodoOne(id);
		todo.setFinishedDate(LocalDate.now());
		todoService.setFinishedDate(todo);
		return "redirect:/viewTodoList";
	}
}