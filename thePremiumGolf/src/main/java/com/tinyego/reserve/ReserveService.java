package com.tinyego.reserve;

import java.util.List;

public interface ReserveService {
	int count(ReserveVo vo);
	List<ReserveVo> listAll(ReserveVo vo);
	ReserveVo edit(ReserveVo vo);
	void update(ReserveVo vo);
	
	List<ReserveVo> list(ReserveVo vo);	
	ReserveVo selectOne(ReserveVo vo);
	
	List<String> availableTimes(ReserveVo vo);
	int insert(ReserveVo vo); // 결제 성공시 값을 리턴받기위해
}
