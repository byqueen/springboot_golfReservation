package com.tinyego.golf;

import java.util.List;

public interface GolfService {
	List<GolfVo> list(GolfVo vo);
	List<GolfVo> listAll();

	GolfVo edit(GolfVo vo);
	void insert(GolfVo vo);
	void delete(GolfVo vo);
	void update(GolfVo vo);
	
	int count(GolfVo vo);
}
