package spring.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import spring.todolist.form.UserForm;

@Controller
@Slf4j
public class UserController {

	@GetMapping("/index")
	public String getIndex(Model model) {
		log.info("ログイン画面を表示します。");
		model.addAttribute("userForm", new UserForm());
		return "index";
	}
}
