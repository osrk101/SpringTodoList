package spring.todolist.controller;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import spring.todolist.domain.user.model.CustomUserDetails;
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

	/* ログインユーザー情報をセット（姓名表示用） */
	@ModelAttribute("loginUser")
	public CustomUserDetails setLoginUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		return customUserDetails;
	}

	/* 表示するページのリクエストパスを取得*/
	@ModelAttribute("currentPath")
	public String setCurrentPath(HttpServletRequest request) {
		return request.getRequestURI();
	}

	/* 完了日を設定する　もしくはクリアする */
	public void finishedControl(TodoForm todoForm) {
		if (todoForm.isFinished() && todoForm.getFinishedDate() == null) {
			todoForm.setFinishedDate(LocalDate.now());
			log.debug("Todoが完了しました。完了日を設定={}", todoForm.getFinishedDate());
		} else if (!todoForm.isFinished()) {
			todoForm.setFinishedDate(null);
			log.debug("Todoの完了日をクリアしました。");
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
	public String postAddTodo(@Validated TodoForm todoForm, BindingResult bindingResult) {
		log.info("Todo追加作業の開始します。入力内容={}", todoForm);
		if (bindingResult.hasErrors()) {
			log.warn("バリデーションエラーが発生しました。：{}", bindingResult.getAllErrors());
			return "addTodo";
		}
		finishedControl(todoForm);
		Todo todo = modelMapper.map(todoForm, Todo.class);
		todoService.addTodo(todo);
		log.info("新規Todoを追加しました。");
		return "redirect:/viewTodoList";
	}

	/** 指定されたTodoのIDを元にデータベースから1件取得する */
	@GetMapping("/updateTodo")
	public String getUpdateTodo(@RequestParam("id") Integer id, Model model) {
		log.info("Todo更新画面を表示します");
		Todo todo = todoService.getTodoOne(id);
		TodoForm todoForm = modelMapper.map(todo, TodoForm.class);
		if (todoForm.getFinishedDate() != null) {
			todoForm.setFinished(true);
		}
		List<MUser> assigneeList = userService.getUsersFullNameList();
		model.addAttribute("assigneeList", assigneeList);
		model.addAttribute("todoForm", todoForm);
		return "updateTodo";
	}

	/** Todoの更新をする*/
	@PostMapping("/updateTodo")
	public String postUpdateTodo(@Validated TodoForm todoForm, BindingResult bindingResult) {
		log.info("Todo更新作業を開始します。更新対象ID={} 入力内容={}", todoForm.getId(), todoForm);
		if (bindingResult.hasErrors()) {
			return "updateTodo";
		}
		finishedControl(todoForm);
		Todo todo = modelMapper.map(todoForm, Todo.class);
		todoService.updateTodo(todo);
		log.info("Todoを更新しました。ID={}", todoForm.getId());
		return "redirect:/viewTodoList";
	}

	/** 選択したTodoの削除確認 */
	@GetMapping("/confirmDelete")
	public String getConfirmDelete(@RequestParam("id") Integer id, Model model) {
		log.info("Todo削除確認画面を表示します。");
		Todo todo = todoService.getTodoOne(id);
		MUser assignee = userService.getUserFullNameOne(todo.getUserId());
		model.addAttribute("todo", todo);
		model.addAttribute("assignee", assignee);
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
	public String todoFinished(TodoForm todoForm) {
		log.info("Todo完了作業を開始します。");
		todoForm.setFinishedDate(LocalDate.now());
		Todo todo = modelMapper.map(todoForm, Todo.class);
		todoService.setFinishedDate(todo);
		return "redirect:/viewTodoList";
	}
}