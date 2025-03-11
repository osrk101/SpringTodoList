package spring.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import spring.todolist.domain.user.model.User;
import spring.todolist.domain.user.service.UserService;
import spring.todolist.form.UserForm;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@ModelAttribute("userForm")
	public UserForm setupUserForm() {
		return new UserForm();
	}

	@GetMapping("/index")
	public String getIndex(@ModelAttribute UserForm userForm,Model model) {
		model.addAttribute("userForm", userForm);
		return "index";
	}

	@PostMapping("/index")
	public String postIndex(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session,
			Model model) {
		if (bindingResult.hasErrors())
			return getIndex(userForm, model);
		User loginUser = userService.verifyLogin(userForm);
		if (loginUser == null) {
			model.addAttribute("loginError", "ユーザー名またはパスワードが間違っています");
			return getIndex(userForm, model);
		}
		session.setAttribute("loginUser", loginUser);
		return "redirect:/viewTodoList";
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpSession session){
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/index";
	}
}