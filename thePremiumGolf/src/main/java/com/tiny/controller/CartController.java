package com.tiny.controller;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tinyego.HolidayChecker;
import com.tinyego.cart.CartService;
import com.tinyego.cart.CartVo;
import com.tinyego.login.SecurityUser;
import com.tinyego.reserve.ReserveService;
import com.tinyego.reserve.ReserveVo;

@Controller
@RequestMapping("/golf/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ReserveService reserveService;
	
	@PostMapping("/insert")
	@ResponseBody
	public ResponseEntity<Map<String, String>> insertCart(@RequestParam(value="cno") String cno, 
														  Principal principal, CartVo vo) {
	    Map<String, String> response = new HashMap<>();
	    String username = principal.getName();
		vo.setMid(username);
	    CartVo m = cartService.edit(vo);
	    if (m != null) {
	        response.put("alertMessage", "장바구니에 담긴 상품이 존재합니다");
	        response.put("redirectUrl", "/golf/course/edit?cno=" + cno);
	    } else {
	        response.put("alertMessage", "장바구니에서 예약을 완료해주세요");
	        response.put("redirectUrl", "/golf/cart/insertOkay?gno=" + vo.getGno() + "&cno=" + vo.getCno() + "&amount=" + vo.getAmount() + "&rpeople=" + vo.getRpeople());
	    }
	    return ResponseEntity.ok()
	            .contentType(MediaType.APPLICATION_JSON) // Content-Type 설정
	            .body(response); // 응답 본문
	}

	@GetMapping("/insertOkay")
	public String insertCartOkay(@RequestParam(value = "gno", required = false) String gno, 
								 @RequestParam(value = "amount", required = false) int amount,
								 @RequestParam(value = "rpeople", required = false) int rpeople,
								 Principal principal, CartVo vo) {
		String username = principal.getName();
		vo.setMid(username);
		cartService.insert(vo);
		return "redirect:/golf/cart/edit";
	}
	
	@GetMapping("/edit")
	public String editReserv(@AuthenticationPrincipal SecurityUser principal, 
			Model model, CartVo vo) {
		String username = principal.getUsername();
		vo.setMid(username);
		
		String authStr = principal.getAuthorities().toString();
		int index = authStr.lastIndexOf(",");
		String role = authStr.substring(6, index);
		
		model.addAttribute("username", username);
		model.addAttribute("role", role);
	    model.addAttribute("m", cartService.edit(vo));
	    return "/golf/cart/list";
	}

    @GetMapping("/holiday")
    @ResponseBody
    public boolean getHoliday(
    		@RequestParam(value = "selectedDate", required = false) 
    			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
    			LocalDate selectedDate) {
        if (selectedDate == null) {
            selectedDate = LocalDate.now(); // 기본값 설정
        }
        return isWeekendOrHoliday(selectedDate);
    }

    private boolean isWeekendOrHoliday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        }
        return HolidayChecker.isHoliday(date);
    }
    
    @GetMapping("/availableTimes")
    @ResponseBody
    public List<String> getAvailableTimes(
    		@RequestParam(value = "selectedDate", required = false) 
    			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
    	    @RequestParam(value = "golfcourseNm", required = false) String golfcourseNm,
    	    @RequestParam(value = "courseNm", required = false) String courseNm, 
    	    ReserveVo vo) 
    {
    	vo.setGolfcourseNm(golfcourseNm);
    	vo.setCourseNm(courseNm);
    	// System.out.println("===> getAvailableTimes vo : "+vo);
    	
        if (selectedDate == null) {
            selectedDate = LocalDate.now(); // 기본값 설정
        }
        return reserveService.availableTimes(vo);
    }
    
    @GetMapping("/delete")
    public String delete(CartVo vo) {
    	cartService.delete(vo);
    	return "redirect:/golf/course/list"; 
    }
}

