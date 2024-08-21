package com.tinyego.golf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GolfServiceImpl implements GolfService {
	GolfServiceImpl() {
		System.out.println("===> GolfServiceImpl()");
	}
	
	@Autowired
	private GolfDao dao;

	@Override
	public List<GolfVo> list(GolfVo vo) {
		return dao.list(vo);
	}
	
	@Override
	public List<GolfVo> listAll() {
		return dao.listAll();
	}

	@Override
	public GolfVo edit(GolfVo vo) {
		return dao.edit(vo);
	}

	@Override
	public void insert(GolfVo vo) {
		dao.insert(vo);
	}

	@Override
	public void delete(GolfVo vo) {
		dao.delete(vo);
	}

	@Override
	public void update(GolfVo vo) {
		dao.update(vo);
	}

	@Override
	public int count(GolfVo vo) {
		return dao.count(vo);
	}

}
