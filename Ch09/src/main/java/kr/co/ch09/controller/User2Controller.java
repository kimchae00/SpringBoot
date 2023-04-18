package kr.co.ch09.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ch09.service.User1Service;
import kr.co.ch09.service.User2Service;
import kr.co.ch09.vo.User1VO;
import kr.co.ch09.vo.User2VO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class User2Controller {
	
	@Autowired
	private User2Service service;

	@ResponseBody
	@CrossOrigin(origins ="*")
	@GetMapping("/user2s")
	public List<User2VO> list() {
		
		List<User2VO> users = service.selectUser2s();
		
		/*
		// builder 패턴 초기화
		User1VO user1 = User1VO.builder()
						.uid("a101")
						.name("홍길동")
						.hp("010-1212-1111")
						.age(20)
						.build();
		*/
		return users;
	}
	@ResponseBody
	@CrossOrigin(origins ="*")
	@GetMapping("/user2")
	public User2VO user2(String uid) {
		return service.selectUser2(uid);
	}
	
	@ResponseBody
	@CrossOrigin(origins ="*")
	@GetMapping("/user2/{uid}")
	public User2VO list(@PathVariable("uid") String uid) {
		return service.selectUser2(uid);
	}
	
	@ResponseBody
	@CrossOrigin(origins ="*")
	@PostMapping("/user2")
	public List<User2VO> register(@RequestBody User2VO vo) {
		
		log.info("vo : "+vo);
		
		service.insertUser2(vo);
		return service.selectUser2s();
	}
	
	@ResponseBody
	@CrossOrigin(origins ="*")
	@PutMapping("/user2")
	public List<User2VO> modify(@RequestBody User2VO vo) {
		service.updateUser2(vo);
		return service.selectUser2s();
	}
	
	@ResponseBody
	@CrossOrigin(origins ="*")
	@DeleteMapping("/user2/{uid}")
	public List<User2VO> delete(@PathVariable("uid") String uid) {
		service.deleteUser2(uid);
		return service.selectUser2s();
	}
}
