package com.tinyego.login;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.tinyego.member.MemberVo;

public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;

	public SecurityUser(MemberVo vo) {
		super(vo.getUsername(), 
			  vo.getPassword(),
			  AuthorityUtils.createAuthorityList(
					  vo.getRole().toString()
					  , vo.getUsername().toString()));
  	}
}







