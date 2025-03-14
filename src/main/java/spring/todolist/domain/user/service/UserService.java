package spring.todolist.domain.user.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {
	
	/** ログインユーザー情報取得　*/
	public UserDetails getLoginUser(String userId);

	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList();

}
