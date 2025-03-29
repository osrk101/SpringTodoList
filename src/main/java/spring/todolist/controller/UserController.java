package spring.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.slf4j.Slf4j;
import spring.todolist.form.UserForm;

@Controller
@Slf4j
public class UserController {

	@GetMapping("/index")
	public String getIndex(@ModelAttribute UserForm userForm) {
		log.info("ログイン画面を表示します。");
		return "index";
	}
}
