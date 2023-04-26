package kr.co.voard.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.voard.vo.TermsVO;
import kr.co.voard.vo.UserVO;

@Mapper
@Repository
public interface UserDAO {

	public int insertUser(UserVO vo);
	public TermsVO selectTerms();
	public void selectUser();
	public void selectUsers();
	public void updateUser();
	public void deleteUser();
	
}
