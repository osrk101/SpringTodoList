package spring.todolist.domain.user.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.todolist.domain.user.model.Todo;
import spring.todolist.domain.user.service.TodoService;
import spring.todolist.form.TodoForm;
import spring.todolist.repository.TodoMapper;

@Service

@Transactional
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoMapper todoMapper;

	@Autowired
	private ModelMapper modelMapper;

	/** Todoリスト全取得 */
	@Override
	public List<Todo> getAllTodo() {
		return todoMapper.getAllTodo();
	}

	/** Todo検索 */
	@Override
	public List<Todo> getSearchTodo(String searchWords) {
		return todoMapper.getSearchTodo(searchWords);
	}

	/** Todo登録 */
	@Override
	public void addTodo(TodoForm todoForm) {
		Todo todo = modelMapper.map(todoForm, Todo.class);
		todoMapper.addTodo(todo);
	}

	/** Todo1件取得 */
	@Override
	public Todo getTodoOne(int todoId) {
		return todoMapper.getTodoOne(todoId);
	}

	/** Todo更新 */
	@Override
	public void updateTodo(TodoForm todoForm) {
		Todo todo = modelMapper.map(todoForm, Todo.class);
		todoMapper.updateTodo(todo);
	}

	/** Todo削除確認 */
	@Override
	public void confirmDelete(TodoForm todoForm) {
	}

	/** Todo削除 */
	@Transactional
	@Override
	public void deleteTodo(int todoId) {
		todoMapper.deleteTodo(todoId);
	}

	/** Todo完了　*/
	@Transactional
	@Override
	public void setFinishedDate(TodoForm todoForm) {
		Todo todo = modelMapper.map(todoForm, Todo.class);
		todoMapper.finishedTodo(todo);
	}
}
