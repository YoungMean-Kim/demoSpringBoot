package kr.co.board.demo.test.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@GetMapping("/bbs")
	@ResponseBody
	public String index() {
		System.out.println("in the MainController[index]");
		return "aaaaaaaaaaa";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
}
