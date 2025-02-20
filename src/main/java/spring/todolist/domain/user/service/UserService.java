package spring.todolist.domain.user.service;

import java.util.List;

import spring.todolist.domain.user.model.User;
import spring.todolist.form.UserForm;

public interface UserService {
	/** ログイン確認 */
	public User verifyLogin(UserForm userForm);

	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList();

}
