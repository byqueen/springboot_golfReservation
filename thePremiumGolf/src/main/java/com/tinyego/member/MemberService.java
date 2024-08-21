package com.tinyego.member;

import java.util.List;

public interface MemberService {
	void join(MemberVo vo);
	int count(MemberVo vo);
	List<MemberVo> list(MemberVo vo);
	MemberVo edit(MemberVo vo);
	void delete(MemberVo vo);
	void update(MemberVo vo);
	
	MemberVo login(MemberVo vo);
}
