package spring.todolist.domain.user.service;

import java.util.List;

import spring.todolist.domain.user.model.Todo;

public interface TodoService {

	/** Todoリスト全取得 */
	public List<Todo> getAllTodo();

	/** Todo検索 */
	public List<Todo> getSearchTodo(String searchWords);

	/** Todo登録 */
	public void addTodo(Todo todo);

	/** Todo1件取得 */
	public Todo getTodoOne(int todoId);

	/** Todo更新 */
	public void updateTodo(Todo todo);

	/** Todo削除 */
	public void deleteTodo(int todoId);

	/** Todo完了処理　*/
	public void setFinishedDate(Todo todo);
}
