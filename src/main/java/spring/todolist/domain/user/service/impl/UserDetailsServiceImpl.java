package spring.todolist.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import spring.todolist.domain.user.service.UserService;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername メソッド開始 - username: {}");
		// ユーザー情報取得 
		UserDetails loginUser = service.getLoginUser(username);

		log.info("ユーザーネームを取得 - username: {}", username);

		// ユーザーが存在しない場合
		if (loginUser == null) {
			log.warn("ユーザーが見つかりませんでした - username: {}", username);
			throw new UsernameNotFoundException("User not found");
		}
		return loginUser; // 認証処理は Security に任せる
	}
}
