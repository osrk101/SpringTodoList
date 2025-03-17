package spring.todolist.domain.user.service;

import java.util.List;

import spring.todolist.domain.user.model.MUser;



public interface UserService {
	
	/** ログインユーザー情報取得　*/
	public MUser getLoginUser(String username);

	/** 姓名リスト取得 */
	public List<MUser> getUsersFullNameList();

}
