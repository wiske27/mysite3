package kr.co.dhflour.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dhflour.mysite.vo.UserVo;

@Controller
public class MainController {

	@RequestMapping( "/main" )
	public String main() {
		return "/main/index";
	}
	
	@ResponseBody
	@RequestMapping("/test")
	public String test() {
		return "안대혁";
	}
	
	@ResponseBody
	@RequestMapping("/test2")
	public UserVo test2() {
		UserVo vo = new UserVo();
		vo.setNo( 10 );
		vo.setName( "둘리" );
		vo.setEmail( "dooly@gmail.com" );
		
		return vo;
	}	
	
}
