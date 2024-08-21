package com.tinyego.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
	void join(MemberVo vo); // insert
	int count(MemberVo vo);
	List<MemberVo> list(MemberVo vo);
	MemberVo edit(MemberVo vo);
	void delete(MemberVo vo);
	void update(MemberVo vo);
	
	MemberVo login(MemberVo vo);
}
