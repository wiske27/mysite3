package kr.co.dhflour.mysite.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.dhflour.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public GuestbookVo get(Long no) {
		GuestbookVo vo = sqlSession.selectOne("guestbook.getByNo", no);
		return vo;
	}
	
	public boolean delete( GuestbookVo vo ) {
		int count = sqlSession.delete( "guestbook.delete", vo );
		return (count == 1);
	}
	
	public boolean insert( GuestbookVo vo ) {
		int count = sqlSession.insert( "guestbook.insert", vo );
		return (count == 1);
	}
	
	public List<GuestbookVo> fetchList() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}
	
	public List<GuestbookVo> fetchList2(Long no) {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList2", no);
		return list;
	}
	
}
