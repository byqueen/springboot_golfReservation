package com.tinyego.course;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseDao {
	List<CourseVo> list(CourseVo vo);
	List<CourseVo> guestList(CourseVo vo);
	CourseVo edit(CourseVo vo);
	List<CourseVo> courseImgList(CourseVo vo);
	void update(CourseVo vo);
	
	void insert(CourseVo vo);
	CourseVo selectCno(CourseVo vo);
	void insertImg(CourseVo vo);
	
	void delete(CourseVo vo);
	
	int count(CourseVo vo);
}
