package com.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/get-api")
public class GetController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping("/name")
	public String getName() {
		return "김채영";
	}
	
	@GetMapping("/variable1/{variable}")
	public String getVariable1(@PathVariable String variable) {
		return variable;
	}
	
	// 'value =' 넣어야 작동
	@GetMapping(value = "/variable2/{variable}")
	public String getVariable2(@PathVariable("variable") String var) {
		return var;
	}
}
