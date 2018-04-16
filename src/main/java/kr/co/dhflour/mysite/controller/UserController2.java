package kr.co.dhflour.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.dhflour.mysite.service.UserService;
import kr.co.dhflour.mysite.vo.UserVo;

@Controller
@SessionAttributes( "authUser" )
@RequestMapping( "/user2" )
public class UserController2 {
	@Autowired
	private UserService userService;
	
	@RequestMapping( value="/login", method=RequestMethod.GET  )
	public String login() {
		return "user/login2";
	}
	
	@RequestMapping( value="/login", method=RequestMethod.POST )
	public String login(@ModelAttribute UserVo vo, Model model) {
		UserVo authUser = userService.getUser(vo);
		model.addAttribute( "authUser", authUser );
		return "redirect:/main";
	}
	
	@RequestMapping( value="/logout" )
	public String auth(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/main";
	}
	
	@ResponseBody
	@RequestMapping( "/modify" )
	public String modify(
		@ModelAttribute("authUser") UserVo authUser) {
		System.out.println( authUser );
		return "UserController2:modify";
	}
}