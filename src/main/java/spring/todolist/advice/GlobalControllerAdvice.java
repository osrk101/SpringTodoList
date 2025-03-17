package spring.todolist.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;
import spring.todolist.domain.user.model.MUser;

@ControllerAdvice
public class GlobalControllerAdvice {

	/** ログインユーザー情報をセッションに保存 */
	@ModelAttribute
	public void addUserToModel(HttpSession session, Model model) {
		MUser loginUser = (MUser) session.getAttribute("loginUser");
		if (loginUser != null) {
			model.addAttribute("loginUser", loginUser);
		}
	}
}