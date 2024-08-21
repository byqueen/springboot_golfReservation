package com.tinyego.golf;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GolfDao {
	List<GolfVo> list(GolfVo vo);
	List<GolfVo> listAll();
	
	GolfVo edit(GolfVo vo);
	void insert(GolfVo vo);
	void delete(GolfVo vo);
	void update(GolfVo vo);
	
	int count(GolfVo vo);
}
