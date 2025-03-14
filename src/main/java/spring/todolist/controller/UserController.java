package spring.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import spring.todolist.form.UserForm;

@Controller
public class UserController {

	@GetMapping("/index")
	public String getIndex(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "index";
	}

	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/index";
	}
}