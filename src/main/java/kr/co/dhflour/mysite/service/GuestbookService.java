package kr.co.dhflour.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dhflour.mysite.repository.GuestbookDao;
import kr.co.dhflour.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getAjaxMessageList(Long no) {
		return guestbookDao.fetchList2(no);
	}
	
	public List<GuestbookVo> getMessageList() {
		return guestbookDao.fetchList();
	}
	
	public void insertMessage(GuestbookVo vo) {
		guestbookDao.insert(vo);
	}
	
	public GuestbookVo insertMessage2(GuestbookVo vo) {
		guestbookDao.insert(vo);
		GuestbookVo guestbookVo = guestbookDao.get(vo.getNo());
		return guestbookVo;
	}
	
	public boolean deleteMessage(GuestbookVo vo) {
		return guestbookDao.delete(vo);
	}	
}
