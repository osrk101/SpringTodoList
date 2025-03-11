package spring.todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.todolist.domain.user.model.Todo;

@Mapper
public interface TodoMapper {

	/** Todoリスト全取得 */
	List<Todo> getAllTodo();

	/** Todo検索 */
	List<Todo> getSearchTodo(String searchWords);

	/** Todo登録 */
	void addTodo(Todo todo);

	/** Todo1件取得 */
	Todo getTodoOne(Integer todoId);

	/** Todo更新 */
	void updateTodo(Todo todo);

	/** Todo削除 */
	void deleteTodo(Integer todoId);

	/** Todo完了処理 */
	void finishedTodo(Todo todo);
}
