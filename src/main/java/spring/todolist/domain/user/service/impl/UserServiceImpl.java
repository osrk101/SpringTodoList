package spring.todolist.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.todolist.domain.user.model.User;
import spring.todolist.domain.user.service.UserService;
import spring.todolist.form.UserForm;
import spring.todolist.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	/** ログイン確認 */
	@Override
	@Transactional
	public User verifyLogin(UserForm userForm) {
		return mapper.verifyLogin(userForm.getUserName(), userForm.getPass());
	}

	@Override
	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList() {
		return mapper.getUsersFullNameList();
	}
}
