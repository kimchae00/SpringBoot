package kr.co.sboard.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.sboard.dao.UserDAO;
import kr.co.sboard.repository.UserRepo;
import kr.co.sboard.vo.TermsVO;
import kr.co.sboard.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Autowired
	private UserRepo repo;
	
	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	
	public int insertUser(UserVO vo) {
		// 비밀번호 암호화
		vo.setPass(passwordEncoder.encode(vo.getPass2()));
		int result = dao.insertUser(vo);
		return result;
	}
	
	public int countUid(String uid) {
		return repo.countByUid(uid);
	}
	public int countNick(String nick) {
		return repo.countByNick(nick);
	}
	
	
	
	
	
	
	
}
