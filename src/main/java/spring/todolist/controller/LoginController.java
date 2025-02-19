package spring.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import spring.todolist.domain.user.model.User;
import spring.todolist.domain.user.service.UserService;
import spring.todolist.form.UserForm;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String getLogin(@ModelAttribute UserForm userForm, Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String PostLogin(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors())
			return getLogin(userForm, model);

		User loginUser = userService.verifyLogin(userForm);
		
		model.addAttribute("loginUser", loginUser);
		return "login";
	}
}
