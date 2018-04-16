package kr.co.dhflour.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dhflour.mysite.repository.GridDao;
import kr.co.dhflour.mysite.vo.GridVo;

@Service
public class GridService {

	@Autowired
	private GridDao gridDao;
	
	public List<GridVo> getGridList() {
		List<GridVo> list = gridDao.getList();
		return list;
	}
	
	public boolean insertGridData (GridVo vo) {
		int count = gridDao.insert(vo);
		
		return count == 1;
	}
	
	public boolean deleteGridData(Long no) {
		int count = gridDao.delete(no);
		
		return count == 1;
	}
	
	public boolean updateGridData(GridVo vo) {
		int count = gridDao.update(vo);
		return count == 1;
	}
	
}
