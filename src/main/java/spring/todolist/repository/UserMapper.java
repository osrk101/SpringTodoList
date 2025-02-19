package spring.todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.todolist.domain.user.model.User;
import spring.todolist.form.UserForm;

@Mapper
public interface UserMapper {

	/** ログイン確認 */
	public User verifyLogin(UserForm userForm);
	
	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList();
}
