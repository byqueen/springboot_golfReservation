package com.tiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tinyego.member.MemberService;
import com.tinyego.member.MemberVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/form") // 로그인 & 회원가입 화면
	public void form() { }

	@PostMapping("/join")
	public String joinmember(MemberVo vo) {
		vo.setMpwd(passwordEncoder.encode(vo.getMpwd()));
		service.join(vo);
		return "redirect:/index";
	}
	
	@GetMapping("/edit")
	public String editMember(Model model, MemberVo vo) {
		model.addAttribute("m", service.edit(vo));
		return "member/edit";
	}
	
	@PostMapping("/update")
	public String updateMember(MemberVo vo) {
		vo.setMpwd(passwordEncoder.encode(vo.getMpwd()));
		service.update(vo);
		return "redirect:/member/list";
	}
	
	@GetMapping("/delete")
	public String deleteMember(MemberVo vo) {
		service.delete(vo);
		return "redirect:/member/list";
	}
	
	@PostMapping("/login")
	public String loginMember(HttpSession session, MemberVo vo) {
		MemberVo m = service.login(vo);
		if (m.getMid() != null) {
			if (m.getMpwd().equals(vo.getPassword())) { // 로그인 성공
				session.setAttribute("ssid", m.getMid());
				session.setAttribute("ssrole", m.getRole().substring(5));
				return "redirect:/index";
			} else { // 로그인 실패
				return "redirect:/member/form";
			}
		} else { // 로그인 실패
			return "redirect:/member/form";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/member/form";
	}
	
	@GetMapping("/success")
	public String successLogin() {
		return "redirect:/index";
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		System.out.println("===> accessDenied");
	}
	
	@GetMapping("/list")
	public String memberList(
			@RequestParam(required = false, defaultValue = "0") Integer start, 
			@RequestParam(value = "searchcondition", required = false) String searchcondition, 
			@RequestParam(value = "searchkey", required = false) String searchkey, 
			Model model, MemberVo vo) {
		
		int pageSize = 10;
	    vo.setStart(start);
        vo.setPageSize(pageSize);
		model.addAttribute("start", start);
		model.addAttribute("pageSize", pageSize);
        
        if (searchkey != null) {
			if (!searchkey.isEmpty() && !searchkey.startsWith("%")) {
					searchkey = '%'+searchkey+'%';
					vo.setSearchkey(searchkey);
			}
		}
		model.addAttribute("searchcondition", vo.getSearchcondition());
		model.addAttribute("searchkey", vo.getSearchkey());

		int currentPage = start / pageSize + 1;
		int totalRecord = service.count(vo);
		int totalPage = (int)(Math.ceil((double)totalRecord / pageSize));
		int listSize = 5;
		int listStartPage = (currentPage-1) / listSize * listSize + 1;
		int listEndPage =  listStartPage + listSize -1;
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listSize", listSize);
		model.addAttribute("listStartPage", listStartPage);
		model.addAttribute("listEndPage", listEndPage);

		int prePage = (pageSize * listSize) * ((listStartPage - listSize - 1) / listSize);
		int nextPage = (pageSize * listSize) * (listEndPage / listSize);
		int lastPage = (totalPage-1) * pageSize;
		int nextPageEnd = (totalPage / listSize) * listSize;
		model.addAttribute("prePage", prePage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("nextPageEnd", nextPageEnd);
		
		model.addAttribute("li", service.list(vo));
		return "/member/list";
	}

}
