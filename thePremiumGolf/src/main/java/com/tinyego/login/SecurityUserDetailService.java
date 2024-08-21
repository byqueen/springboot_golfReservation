package com.tinyego.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tinyego.member.MemberService;
import com.tinyego.member.MemberVo;

@Service
public class SecurityUserDetailService implements UserDetailsService {

	@Autowired
	MemberService service;
		
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		MemberVo vo = new MemberVo();
		vo.setUsername(username);
		
		MemberVo user = service.login(vo);
		if (user == null) {
			throw new UsernameNotFoundException(username + "사용자 없음");
		}else {
			return new SecurityUser(user);
		}
	}
}








