package spring.todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.todolist.domain.user.model.Todo;

@Mapper
public interface TodoMapper {

	/** Todoリスト全取得 */
	List<Todo> getAllTodo();

	/** Todo検索 */
	List<Todo> getSearchTodo();
}
