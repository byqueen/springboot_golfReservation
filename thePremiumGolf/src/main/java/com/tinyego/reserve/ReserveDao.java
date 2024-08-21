package com.tinyego.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReserveDao {
	int count(ReserveVo vo);
	List<ReserveVo> listAll(ReserveVo vo);
	ReserveVo edit(ReserveVo vo);
	void update(ReserveVo vo);
	
	List<ReserveVo> list(ReserveVo vo);	
	ReserveVo selectOne(ReserveVo vo);
	
	List<String> availableTimes(ReserveVo vo);
	int insert(ReserveVo vo); // 결제 성공시 값을 리턴받기위해
}
