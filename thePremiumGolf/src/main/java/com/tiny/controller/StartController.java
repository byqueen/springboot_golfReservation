package com.tiny.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tinyego.golf.GolfVo;
import com.tinyego.index.IndexService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StartController {
	StartController() {
		System.out.println("===> StartController()");
	}
	
	@Autowired
	IndexService service;
	
	@GetMapping("/index")
	public String index(Model model) { 
		System.out.println("===> index mapping");
		List<GolfVo> li = service.list();
		Collections.shuffle(li);
		model.addAttribute("li", li);
		return "/index";
	}
	
	@GetMapping("/top")
	public String top(HttpSession session, Model model) {
		String ssId = (String) session.getAttribute("ssId");
		model.addAttribute("ssId", ssId);
		return "/include/top";
	}
}
