package kr.co.board.demo.common.dto;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDto {
	private String message;
	private String redriectUri;
	private RequestMethod method;
	private Map<String, String> data;
}
