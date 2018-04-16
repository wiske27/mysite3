package kr.co.dhflour.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dhflour.mysite.repository.UserDao;
import kr.co.dhflour.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
//	@Autowired
//	private MailSender mailSender;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Throwable.class})
	public void join(UserVo vo) {
		userDao.insert(vo);
		userDao.fetch(vo);
	}
	
	public UserVo getUser(UserVo vo) {
		return userDao.fetch(vo);
	}

	public UserVo getUser(Long no) {
		return userDao.fetch(no);
	}
	
	public UserVo checkEmail(String email) {
		UserVo result = userDao.fetch( email );
		return result;
	}
}
