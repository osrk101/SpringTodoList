package spring.todolist.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.todolist.domain.user.model.Todo;
import spring.todolist.domain.user.service.TodoService;
import spring.todolist.form.TodoForm;
import spring.todolist.repository.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoMapper todoMapper;

	/** Todoリスト全取得 */
	@Override
	@Transactional
	public List<Todo> getAllTodo() {
		return todoMapper.getAllTodo();
	}

	/** Todo検索 */
	@Override
	public List<Todo> getSearchTodo(String searchWords) {
		return todoMapper.getSearchTodo(searchWords);
	}

	/** Todo登録  */
	@Override
	public boolean addTodo(TodoForm todoForm) {
		return todoMapper.addTodo(todoForm);
	}

	/** Todo更新 */
	@Override
	public void updateTodo(TodoForm todoForm) {

	}

	/** Todo削除 */
	@Override
	public void deleteTodo(TodoForm todoForm) {

	}

	/** Todo削除確認 */
	@Override
	public void ConfirmDelete(TodoForm todoForm) {

	}

	/** Todo完了処理　*/
	@Override
	public void setFinishedDate() {

	}
}
