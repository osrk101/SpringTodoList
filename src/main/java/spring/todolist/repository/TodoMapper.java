package spring.todolist.repository;

import java.util.List;

import spring.todolist.domain.user.model.Todo;

public interface TodoMapper {

	/** Todoリスト全取得 */
	List<Todo> getAllTodo();

}
