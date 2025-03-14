package spring.todolist.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalControllerAdvice {

	/** ログインユーザー情報をセッションに保存 */
	/*@ModelAttribute
	public void addUserToModel(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			model.addAttribute("loginUser", loginUser);
		}
	}*/
}