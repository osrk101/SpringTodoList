package spring.todolist.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import spring.todolist.domain.user.model.MUser;
import spring.todolist.domain.user.service.UserService;
import spring.todolist.repository.UserMapper;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	/** ログインユーザ情報取得 */
	@Override
	public MUser getLoginUser(String username) {
		log.info("ログインユーザー情報を取得します。");
		return mapper.findLoginUser(username);
	}

	@Override
	/** 姓名リスト取得  */
	public List<MUser> getUsersFullNameList() {
		log.info("ユーザー姓名リストを取得します。");
		return mapper.getUsersFullNameList();
	}
	
	@Override
	/** 姓名取得(1件) */
	public MUser getUserFullNameOne(int userId) {
		log.info("ユーザーの姓名を1件取得します。");
		return mapper.getUserFullNameById(userId);
	}
}
