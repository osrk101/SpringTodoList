package spring.todolist.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import spring.todolist.domain.user.model.MUser;
import spring.todolist.domain.user.model.Todo;
import spring.todolist.domain.user.service.TodoService;
import spring.todolist.repository.TodoMapper;
import spring.todolist.repository.UserMapper;

@Service
@Slf4j
@Transactional
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoMapper todoMapper;

	@Autowired
	private UserMapper userMapper;

	/** Todoリスト全取得 */
	@Override
	public List<Todo> getAllTodo() {
		log.info("全てのTodoを取得します。");
		List<Todo> todoList = todoMapper.getAllTodo();
		for (Todo todo : todoList) {
			MUser assignee = userMapper.getUserFullNameById(todo.getUserId());
			todo.setAssignee(assignee);
			log.debug("セットされた担当者:{}", todo.getAssignee());
		}
		log.info("取得したTodoの件数: {}", todoList.size());
		return todoList;
	}

	/** Todo検索 */
	@Override
	public List<Todo> getSearchTodo(String searchWords) {
		log.info("検索ワード '{}' でTodoを検索します。", searchWords);
		List<Todo> todoList = todoMapper.getSearchTodo(searchWords);
		for (Todo todo : todoList) {
			MUser assignee = userMapper.getUserFullNameById(todo.getUserId());
			todo.setAssignee(assignee);
			log.debug("セットされた担当者:{}", todo.getAssignee());
		}
		log.info("検索結果の件数: {}", todoList.size());
		return todoList;
	}

	/** Todo登録 */
	@Override
	public void addTodo(Todo todo) {
		log.info("新規Todoを追加します。", todo);
		try {
			todoMapper.addTodo(todo);
			log.info("Todoの追加に成功しました。");
		} catch (Exception e) {
			log.error("Todoの追加に失敗しました。内容：{}", todo, e);
			throw e;
		}
	}

	/** Todo1件取得 */
	@Override
	public Todo getTodoOne(int todoId) {
		log.info("Todo(ID={})を取得します", todoId);
		return todoMapper.getTodoOne(todoId);
	}

	/** Todo更新 */
	@Override
	public void updateTodo(Todo todo) {
		log.info("Todo(ID={})を更新します。", todo.getId(), todo);
		int updateCount = todoMapper.updateTodo(todo);
		if (updateCount == 1) {
			log.info("Todo更新に成功しました。");
		} else {
			log.warn("Todo更新に失敗しました。");
		}
	}

	/** Todo削除 */
	@Transactional
	@Override
	public void deleteTodo(int todoId) {
		int deleteCount = todoMapper.deleteTodo(todoId);
		if (deleteCount == 1) {
			log.info("Todo削除に成功しました。");
		} else {
			log.info("Todo削除に失敗しました。");
		}
	}

	/** Todo完了　*/
	@Transactional
	@Override
	public void setFinishedDate(Todo todo) {
		int finishedCount = todoMapper.finishedTodo(todo);
		if (finishedCount == 1) {
			log.info("Todo完了作業に成功しました。");
		} else {
			log.info("Todo完了作業に失敗しました。");
		}
	}
}
