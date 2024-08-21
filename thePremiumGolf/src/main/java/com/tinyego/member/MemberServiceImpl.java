package com.tinyego.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	MemberServiceImpl() {
		System.out.println("===> UserServiceImpl()");
	}
	
	@Autowired
	private MemberDao dao;

	@Override
	public List<MemberVo> list(MemberVo vo) {
		return dao.list(vo);
	}

	@Override
	public MemberVo edit(MemberVo vo) {
		return dao.edit(vo);
	}

	@Override
	public void join(MemberVo vo) {
		dao.join(vo);
	}

	@Override
	public void delete(MemberVo vo) {
		dao.delete(vo);
	}

	@Override
	public void update(MemberVo vo) {
		dao.update(vo);
	}

	@Override
	public int count(MemberVo vo) {
		return dao.count(vo);
	}

	@Override
	public MemberVo login(MemberVo vo) {
		return dao.login(vo);
	}
}
