package spring.todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import spring.todolist.domain.user.model.User;

@Mapper
public interface UserMapper {

	/** ログイン確認 */
	public User verifyLogin(@Param("userName") String userName, @Param("pass") String pass);
	
	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList();
}
