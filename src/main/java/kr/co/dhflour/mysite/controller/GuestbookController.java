package kr.co.dhflour.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dhflour.mysite.service.GuestbookService;
import kr.co.dhflour.mysite.vo.GuestbookVo;

@Controller
@RequestMapping( "/guestbook" )
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping( { "", "/list" } )
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
		model.addAttribute( "list", list );
		return "/guestbook/list";
	}
	
	@RequestMapping( value="/insert", method=RequestMethod.POST )
	public String insert(@ModelAttribute GuestbookVo vo) {
		guestbookService.insertMessage( vo );
		return "redirect:/guestbook";
	}

	@RequestMapping( "/deleteform/{no}" )
	public String deleteform(Model model, @PathVariable("no") Long no) {
		model.addAttribute( "no", no );
		return "guestbook/deleteform";
	}
	
	@RequestMapping( value="/delete", method=RequestMethod.POST )
	public String delete(@ModelAttribute GuestbookVo vo) {
		guestbookService.deleteMessage(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping( "/ajax" )
	public String ajax() {
		return "guestbook/ajax";
	}
	
	@ResponseBody
	@RequestMapping( "/ajax/list" )
	public Object ajaxList(@RequestParam(value = "no", required=true, defaultValue="0") Long no) {
		List<GuestbookVo> list = guestbookService.getAjaxMessageList(no);
		return list;
	}
	
	@ResponseBody
	@RequestMapping( "/ajax/insert" )
	public Object ajaxInsert(@ModelAttribute GuestbookVo vo) {
		System.out.println(vo);
		GuestbookVo guestbookVo = guestbookService.insertMessage2(vo);		
		return guestbookVo;
	}
	
	@ResponseBody
	@RequestMapping( value="/ajax/delete", method=RequestMethod.POST )
	public Map<String, Object> ajaxDelete(@ModelAttribute GuestbookVo vo) {
		boolean result = guestbookService.deleteMessage(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("no", vo.getNo());
		
		return map;
	}
}
