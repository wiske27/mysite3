package kr.co.dhflour.mysite.repository;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.dhflour.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean update( UserVo vo ) {
		int count = sqlSession.update( "user.update", vo );
		return count == 1;			
	}
	
	public UserVo fetch(long no) {
		UserVo result = sqlSession.selectOne( "user.getByNo", no );
		return result;				
	}
	
	public UserVo fetch(String email) {
		UserVo result = sqlSession.selectOne( "user.getByEmail", email );
		return result;
	}
	
	public UserVo fetch(UserVo vo) {
		UserVo result = sqlSession.selectOne( "user.getByEmailAndPassword", vo );
		return result;
	}
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert( "user.insert", vo );
		return (count == 1);		
	}
}
