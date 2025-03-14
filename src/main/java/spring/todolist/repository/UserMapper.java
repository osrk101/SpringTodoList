package spring.todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

@Mapper
public interface UserMapper {

	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList();

	/** ログインユーザー情報取得　*/
	public User findLoginUser(String username);
	
}