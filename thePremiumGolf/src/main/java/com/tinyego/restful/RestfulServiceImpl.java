package com.tinyego.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tinyego.golf.GolfVo;

@Service
@RequestMapping("/restful")
public class RestfulServiceImpl implements RestfulService{
	RestfulServiceImpl() {
		System.out.println("===> RestfulServiceImpl()");
	}
	
	@Autowired
	RestfulDao dao;

	@Override
	public void insert(GolfVo vo) {
		dao.insert(vo);
	}
}
