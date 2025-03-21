package spring.todolist.advice;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import spring.todolist.domain.user.model.CustomUserDetails;

@ControllerAdvice
public class GlobalControllerAdvice {

	/** ログインユーザー情報を保存 */
	@ModelAttribute("loginUser")
	public CustomUserDetails loginUser(Authentication authentication) {
		if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
			return (CustomUserDetails) authentication.getPrincipal();
		}
		return null;
	}
}
