package com.tinyego.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
	CourseServiceImpl() {
		System.out.println("===> CourseServiceImpl()");
	}
	
	@Autowired
	CourseDao dao;

	@Override
	public List<CourseVo> list(CourseVo vo) {
		return dao.list(vo);
	}
	
	@Override
	public List<CourseVo> guestList(CourseVo vo) {
		return dao.guestList(vo);
	}

	@Override
	public CourseVo edit(CourseVo vo) {
		return dao.edit(vo);
	}
	
	@Override
	public List<CourseVo> courseImgList(CourseVo vo) {
		return dao.courseImgList(vo);
	}

	@Override
	public void update(CourseVo vo) {
		dao.update(vo);
	}

	@Override
	public void insert(CourseVo vo) {
		dao.insert(vo);
	}
	
	@Override
	public CourseVo selectCno(CourseVo vo) {
		return dao.selectCno(vo);
	}
	
	@Override
	public void insertImg(CourseVo vo) {
		dao.insertImg(vo);
	}

	@Override
	public void delete(CourseVo vo) {
		dao.delete(vo);
	}

	@Override
	public int count(CourseVo vo) {
		return dao.count(vo);
	}
}
