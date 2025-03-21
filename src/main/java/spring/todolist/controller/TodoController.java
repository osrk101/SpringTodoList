package spring.todolist.controller;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
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
		return "viewTodoList";
	}

	/** Todo追加ページへ担当者リストを取得して表示させる */
	@GetMapping("/addTodo")
	public String getAddTodo(TodoForm todoForm, Model model) {
		log.info("Todo追加画面を表示します。");
		List<MUser> assigneeList = userService.getUsersFullNameList();
		model.addAttribute("assigneeList", assigneeList);
		model.addAttribute(todoForm);
		return "addTodo";
	}

	/** 入力されたTodoのバリデーションチェックをしてデータベースに追加、その後リスト画面へ戻る */
	@PostMapping("/addTodo")
	public String postAddTodo(@Valid TodoForm todoForm, BindingResult bindingResult, Model model) {
		log.info("Todo追加作業の開始します。入力内容={}", todoForm);
		if (bindingResult.hasErrors()) {
			log.warn("バリデーションエラーが発生しました。：{}", bindingResult.getAllErrors());
			return getAddTodo(todoForm, model);
		}
		todoService.addTodo(todoForm);
		log.info("新規Todoを追加しました。");
		return "redirect:/viewTodoList";
	}

	/** 指定されたTodoのIDを元にデータベースから1件取得する */
	@GetMapping("/updateTodo")
	public String getUpdateTodo(@RequestParam("id") Integer id, Model model) {
		log.info("Todo更新画面を表示します");
		List<MUser> assigneeList = userService.getUsersFullNameList();
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
	public String postUpdateTodo(@Valid TodoForm todoForm, BindingResult bindingResult,
			Model model) {
		log.info("Todo更新作業を開始します。更新対象ID={} 入力内容={}", todoForm.getId(), todoForm);
		if (bindingResult.hasErrors()) {
			log.warn("Todo更新時のバリデーションエラーが発生しました。：{}", bindingResult.getAllErrors());
			List<MUser> assigneeList = userService.getUsersFullNameList();
			model.addAttribute("assigneeList", assigneeList);
			return "updateTodo";
		}
		if ("1".equals(todoForm.getStringFinished()) && todoForm.getFinishedDate() == null) {
			todoForm.setFinishedDate(LocalDate.now());
			log.debug("Todoが完了しました。完了日を設定={}", todoForm.getFinishedDate());
		} else if (!"1".equals(todoForm.getStringFinished())) {
			todoForm.setFinishedDate(null);
			log.debug("Todoの完了日をクリアしました。");
		}
		todoService.updateTodo(todoForm);
		log.info("Todoを更新しました。ID={}", todoForm.getId());
		return "redirect:/viewTodoList";
	}

	/** 選択したTodoの削除確認 */
	@GetMapping("/confirmDelete")
	public String getConfirmDelete(@RequestParam("id") Integer id, Model model) {
		log.info("Todo削除確認画面を表示します。");
		Todo todo = todoService.getTodoOne(id);
		model.addAttribute("todo", todo);
		return "confirmDelete";
	}

	/** Todoの削除 */
	@GetMapping("/deleteTodo")
	public String getDeleteTodo(@RequestParam Integer id) {
		log.info("Todo削除作業を開始します。");
		todoService.deleteTodo(id);
		return "redirect:/viewTodoList";
	}

	@GetMapping("/finishedTodo")
	public String todoFinished(TodoForm todoForm) {
		log.info("Todo完了作業を開始します。");
		todoForm.setFinishedDate(LocalDate.now());
		todoService.setFinishedDate(todoForm);
		return "redirect:/viewTodoList";
	}
}