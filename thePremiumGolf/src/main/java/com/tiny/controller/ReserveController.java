package com.tiny.controller;


import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tinyego.cart.CartService;
import com.tinyego.cart.CartVo;
import com.tinyego.golf.GolfVo;
import com.tinyego.login.SecurityUser;
import com.tinyego.reserve.ReserveService;
import com.tinyego.reserve.ReserveVo;
import com.tinyego.member.MemberService;
import com.tinyego.member.MemberVo;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/golf/reserve")
public class ReserveController {
	ReserveController() {
		System.out.println("===> ReserveController()");
	}
	
	@Autowired
	ReserveService reserveService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/listAll")
	public String listAll(@RequestParam(required = false, defaultValue = "0") Integer start, 
			 			  @RequestParam(value = "searchcondition", required = false) String searchcondition, 
			 			  @RequestParam(value = "searchkey", required = false) String searchkey, 
			 			  Model model, ReserveVo vo) {
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
		int totalRecord = reserveService.count(vo);
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
		
		model.addAttribute("li", reserveService.listAll(vo));
		return "/golf/reserve/admList";
	}
	
	@GetMapping("/list")
	public String list(Principal principal, Model model, ReserveVo vo) {
		String username = principal.getName();
		vo.setMid(username);
		ReserveVo m = reserveService.selectOne(vo);
		model.addAttribute("m", reserveService.selectOne(vo));
		return "golf/reserve/list";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, ReserveVo vo) {
		model.addAttribute("m", reserveService.edit(vo));
		return "golf/reserve/edit";
	}
	
	@GetMapping("/update")
	public String update(@AuthenticationPrincipal SecurityUser principal, ReserveVo vo) {
		reserveService.update(vo);
		String rno = vo.getRno();
		
		String authStr = principal.getAuthorities().toString();
		int index = authStr.lastIndexOf(",");
		String role = authStr.substring(6, index);
		System.out.println("===> role : " + role);
		
		if (role.equals("ADMIN") || role.equals("MANAGER")) {
			return "redirect:/golf/reserve/edit?rno="+rno;
		} else {
			return "redirect:/golf/reserve/list";
		}
	}
	
	@PostMapping("/insert")
	public String insertReserv(@RequestParam(value = "selectedDate", required = false) String selectedDate,
							   @RequestParam(value = "totalPrice", required = false) int totalPrice,
							   @RequestParam(value = "salePrice", required = false) int salePrice,
							   Principal principal, ReserveVo rvo, CartVo cvo, MemberVo mvo) {
		String username = principal.getName();
		rvo.setMid(username);
		rvo.setSelectedDate(selectedDate);
		
		mvo.setMid(username);
		MemberVo m = memberService.edit(mvo);
		if (rvo.getRname().equals("")) { 
			rvo.setRname(m.getMname());
		};
		if (rvo.getRphone().equals("")) {
			rvo.setRphone(m.getMphone());
		}
		reserveService.insert(rvo);
		cartService.delete(cvo);
		return "redirect:/golf/reserve/list?";
	}
}

