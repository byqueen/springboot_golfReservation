package com.tinyego.restful;

import org.apache.ibatis.annotations.Mapper;

import com.tinyego.golf.GolfVo;

@Mapper
public interface RestfulDao {
	void insert(GolfVo vo);
}
