package com.tiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinyego.cart.CartService;
import com.tinyego.cart.CartVo;
import com.tinyego.reserve.ReserveService;
import com.tinyego.reserve.ReserveVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/portone")
public class PortoneController {
	PortoneController() {
		System.out.println("===> PortoneController()");
	}
	
	@Autowired
	ReserveService reserveService;
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/insert")
	@ResponseBody
	public String insertPortone(@RequestBody ReserveVo rvo, 
								HttpSession session, CartVo cvo, Model model) {
		String returnURL = "";
		int reserve = reserveService.insert(rvo);
		
		String ctno = rvo.getCtno();
		cvo.setCtno(ctno);
		cartService.delete(cvo);
		
		if (reserve == 1) { // 결제 성공
			returnURL = "/golf/reserve/list";
		} else { // 결제 실패
			returnURL = "/golf/cart/edit";
		}
		return returnURL;
	}
}
