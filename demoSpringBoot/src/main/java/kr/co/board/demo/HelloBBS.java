package kr.co.board.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloBBS {
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("TEST[in the HelloController]");
		
		return "Hello BBS";
	}
}
