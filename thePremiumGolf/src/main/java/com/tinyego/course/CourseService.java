package com.tinyego.course;

import java.util.List;

public interface CourseService {
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
