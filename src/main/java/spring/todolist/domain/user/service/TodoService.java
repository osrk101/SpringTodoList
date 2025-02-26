package spring.todolist.domain.user.service;

import java.util.List;

import spring.todolist.domain.user.model.Todo;
import spring.todolist.form.TodoForm;

public interface TodoService {

	/** Todoリスト全取得 */
	public List<Todo> getAllTodo();

	/** Todo検索 */
	public List<Todo> getSearchTodo(String searchWords);

	/** Todo登録 */
	public void addTodo(TodoForm todoForm);

	/** Todo1件取得 */
	public Todo getTodoOne(int todoId);
	
	/** Todo更新 */
	public void updateTodo(TodoForm todoForm);

	/** Todo削除 */
	public void deleteTodo(TodoForm todoForm);

	/** Todo削除確認 */
	public void ConfirmDelete(TodoForm todoForm);

	/** Todo完了処理　*/
	public void setFinishedDate();
}
