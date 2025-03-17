package spring.todolist.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import spring.todolist.domain.user.model.MUser;
import spring.todolist.domain.user.service.UserService;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername メソッド開始 - username: {}", username);
		
		// ユーザー情報取得 
		MUser user = service.getLoginUser(username);
		if (user == null) {
			log.warn("ユーザーが見つかりませんでした - username: {}", username);
			throw new UsernameNotFoundException("User not found");
		}
		UserDetails loginUser = user;
		System.out.println("loginUser:" + loginUser);
		return loginUser; 
	}
}