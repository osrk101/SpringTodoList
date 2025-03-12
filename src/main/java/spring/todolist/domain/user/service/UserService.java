package spring.todolist.domain.user.service;

import java.util.List;

import spring.todolist.domain.user.model.User;

public interface UserService {
	
	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList();

}
