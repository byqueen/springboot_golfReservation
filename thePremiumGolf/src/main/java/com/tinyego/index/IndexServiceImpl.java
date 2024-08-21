package com.tinyego.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinyego.golf.GolfVo;

@Service
public class IndexServiceImpl implements IndexService {
	IndexServiceImpl() {
		System.out.println("===> IndexServiceImpl()");
	}
	
	@Autowired
	IndexDao dao;

	@Override
	public List<GolfVo> list() {
		return dao.list();
	}
}
