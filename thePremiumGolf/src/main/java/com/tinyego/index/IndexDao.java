package com.tinyego.index;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tinyego.golf.GolfVo;

@Mapper
public interface IndexDao {
	List<GolfVo> list();
}
