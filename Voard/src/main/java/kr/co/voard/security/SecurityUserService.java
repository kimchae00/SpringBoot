package kr.co.voard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.voard.repository.UserEntity;
import kr.co.voard.repository.UserRepo;


@Service
public class SecurityUserService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 해당 사용자 있는지 확인
		UserEntity user = repo.findById(username).get();
		
		if(user == null) {
			throw new UsernameNotFoundException(username); // 예외처리(해당 사용자 없음)
		}
		
		MyUserDetails myUserDetails = MyUserDetails.builder()
								.user(user)
								.build();
		
		return myUserDetails;
	}
}
