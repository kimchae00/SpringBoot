package kr.co.ch10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = {"/", "/index"})
	public String index() {
		// 로깅
		logger.trace("trace 로그...");
		logger.debug("debug 로그...");
		logger.info("info 로그...");
		logger.warn("warn 로그...");
		logger.error("error로그...");
		
		return "/index";
	}
}
