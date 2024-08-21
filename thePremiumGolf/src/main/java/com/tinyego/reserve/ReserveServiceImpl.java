package com.tinyego.reserve;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReserveServiceImpl implements ReserveService {
	ReserveServiceImpl() {
		System.out.println("===> ReserveServiceImpl()");
	}
	
	@Autowired
	ReserveDao dao;
	
	@Override
	public int count(ReserveVo vo) {
		return dao.count(vo);
	}
	
	@Override
	public List<ReserveVo> listAll(ReserveVo vo) {
		return dao.listAll(vo);
	}
	
	@Override
	public ReserveVo edit(ReserveVo vo) {
		return dao.edit(vo);
	}
	
	@Override
	public void update(ReserveVo vo) {
		dao.update(vo);
	}
	
	@Override
	public List<ReserveVo> list(ReserveVo vo) {
		return dao.list(vo);
	}

	@Override
	public ReserveVo selectOne(ReserveVo vo) {
		return dao.selectOne(vo);
	}
	
	@Override
	public List<String> availableTimes(ReserveVo vo) {
		return dao.availableTimes(vo);
	}
	
	@Override
	public int insert(ReserveVo vo) {
		return dao.insert(vo);
	}
}
