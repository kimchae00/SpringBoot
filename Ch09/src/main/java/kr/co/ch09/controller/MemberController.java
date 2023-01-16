package kr.co.ch09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ch09.service.MemberService;
import kr.co.ch09.vo.MemberVO;

@RestController // restcontroller는 responsebody 생략 가능
public class MemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping("/member")
	public List<MemberVO> list() {
		List<MemberVO> users = service.selectMembers();
		return users;
	}
	
	@GetMapping("/member/{uid}")
	public MemberVO list(@PathVariable("uid") String uid) {
		return service.selectMember(uid);
	}
	
	@PostMapping("/member")
	public void register(MemberVO vo) {
		
	}
	
	@PutMapping("/member")
	public void modify(MemberVO vo) {
		
	}

	@DeleteMapping("/member/{uid}")
	public void delete(@PathVariable("uid") String uid) {
		
	}
	
}
