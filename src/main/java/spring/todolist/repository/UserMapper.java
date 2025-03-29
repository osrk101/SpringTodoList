package spring.todolist.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import spring.todolist.domain.user.model.MUser;

@Mapper
public interface UserMapper {

	/** 姓名リスト取得 */
	public List<MUser> getUsersFullNameList();

	/** ログインユーザー情報取得　*/
	public MUser findLoginUser(String username);

	/** idから姓名を取得（１件） */
	public MUser getUserFullNameById(int userId);

}