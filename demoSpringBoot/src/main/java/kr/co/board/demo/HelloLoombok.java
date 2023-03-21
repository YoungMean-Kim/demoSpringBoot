package kr.co.board.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import lombok.Setter;

@RequiredArgsConstructor
@Getter
//@Setter
public class HelloLoombok {
	
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		HelloLoombok helloLombok = new HelloLoombok("Hello", 5);
//		helloLombok.setHello("Hello");
//		helloLombok.setLombok(55);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	}
}
