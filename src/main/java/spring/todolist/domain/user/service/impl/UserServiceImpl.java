package spring.todolist.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.todolist.domain.user.model.User;
import spring.todolist.domain.user.service.UserService;
import spring.todolist.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList() {
		return mapper.getUsersFullNameList();
	}
}
