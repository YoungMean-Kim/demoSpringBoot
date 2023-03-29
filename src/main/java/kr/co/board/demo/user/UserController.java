package kr.co.board.demo.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserForm userForm) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserForm userForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup";
		}
		
		if(!userForm.getPassword().equals(userForm.getPasswordCheck())) {
			bindingResult.rejectValue("passwordCheck", "passwordInCorrect", "비밀번호와 비밀번화 확인이 일치하지 않습니다.");
			return "signup";
		}
		try {
			userService.create(userForm.getUsername(), userForm.getPassword(), userForm.getEmail());
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login") 
	public String login() {
		return "/common/login_form";
	}
}
