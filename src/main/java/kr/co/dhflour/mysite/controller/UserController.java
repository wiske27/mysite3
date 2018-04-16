package kr.co.dhflour.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dhflour.mysite.service.UserService;
import kr.co.dhflour.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinform")
	public String joinform() {
		return "/user/joinform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "/user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform() {
		return "/user/loginform";
	}
	
	@RequestMapping("/login")
	public String login( HttpSession session, @ModelAttribute UserVo vo ) {
		UserVo authUser = userService.getUser(vo);
		if( authUser == null ) {
			/* 인증 실패 */
			return "redirect:/user/loginform?r=fail";
		}
		
		/* 인증 처리 */
		session.setAttribute( "authUser", authUser );
		
		return "redirect:/main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute( "authUser" );
		session.invalidate();
		return "redirect:/main";
	}
	
	@ResponseBody
	@RequestMapping( "/checkemail" )
	public Object checkemail(
		@RequestParam(value="email", required=true, defaultValue="") String email) {
		UserVo vo = userService.checkEmail( email );
		Boolean exist = (vo != null);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "exist", exist );
		return map;
	}
}
