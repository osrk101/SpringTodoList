package spring.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import spring.todolist.form.UserForm;

@Controller
public class UserController {
	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping("/index")
	public String getIndex(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "index";
	}

	@PostMapping("/authenticate")
	public String authenticate(@Valid UserForm userForm, BindingResult bindingResult,
			HttpServletRequest request,
			Model model) {
		/*if (bindingResult.hasErrors()) {
			return "index";
		}*/
		try {
			// 認証処理をSpring Securityに任せる
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userForm.getUserName(),
					userForm.getPass());
			Authentication authentication = authenticationManager.authenticate(authToken);

			// 認証成功後、セッションに認証情報を保存
			SecurityContextHolder.getContext().setAuthentication(authentication);
			request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
					SecurityContextHolder.getContext());

			return "redirect:/viewTodoList";

		} catch (AuthenticationException e) {
			model.addAttribute("loginError", "ユーザー名またはパスワードが間違っています");
			return "index";
		}
	}

	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/index";
	}
}