package kr.co.dhflour.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dhflour.mysite.service.GridService;
import kr.co.dhflour.mysite.vo.GridVo;

@Controller
@RequestMapping("/grid")
public class GridController {
	
	@Autowired
	private GridService gridService;
	
	@RequestMapping("")
	public String index() {
		return "/grid/index";
	}
	
	@ResponseBody
	@RequestMapping("/api/list")
	public List<GridVo> list() {
		List<GridVo> list = gridService.getGridList();
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/api/insert")
	public Boolean insert( @RequestBody GridVo vo) {
		System.out.println(vo);
		Boolean result = gridService.insertGridData(vo);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/api/delete")
	public Map<String, Object> delete(@RequestParam(value="no", required=true, defaultValue="0") Long no) {
		Map<String, Object> map = new HashMap<String, Object> () ;
		boolean result = gridService.deleteGridData(no);
		
		map.put("result", result);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/api/update")
	public boolean update(@RequestBody GridVo vo) {
		boolean result = gridService.updateGridData(vo);
		
		System.out.println(vo);
		return result;
	}
}
