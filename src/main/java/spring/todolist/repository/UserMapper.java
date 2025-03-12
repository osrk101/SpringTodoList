package spring.todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.todolist.domain.user.model.User;

@Mapper
public interface UserMapper {

	/** 姓名リスト取得 */
	public List<User> getUsersFullNameList();
}
