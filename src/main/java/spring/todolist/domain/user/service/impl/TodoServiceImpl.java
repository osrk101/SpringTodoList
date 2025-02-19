package spring.todolist.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.todolist.domain.user.model.Todo;
import spring.todolist.repository.TodoMapper;

@Service
public class TodoServiceImpl {

	@Autowired
	private TodoMapper todoMapper;

	/** Todoリスト全取得 */
	public List<Todo> getAllTodo() {
		return todoMapper.getAllTodo();
	}

}
