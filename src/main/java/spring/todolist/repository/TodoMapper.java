package spring.todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.todolist.domain.user.model.Todo;
import spring.todolist.form.TodoForm;

@Mapper
public interface TodoMapper {

	/** Todoリスト全取得 */
	List<Todo> getAllTodo();

	/** Todo検索 */
	List<Todo> getSearchTodo(String searchWords);
	
	/** Todo登録 */
	boolean addTodo(TodoForm todoForm);
}
