package com.tiny.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tinyego.TimeStr;
import com.tinyego.course.CourseService;
import com.tinyego.course.CourseVo;
import com.tinyego.golf.GolfService;
import com.tinyego.golf.GolfVo;
import com.tinyego.login.SecurityUser;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/golf/course")
public class CourseController {
	CourseController() {
		System.out.println("===> CourseController()");
	}
	
	@Autowired
	GolfService golfService;

	@Autowired
	CourseService courseService;
	
	@Autowired
	private ServletContext servletContext;
	
	String path = "";
	@PostConstruct
	public void init() {
		path = servletContext.getRealPath("/files/golf/course/");
	}
	
	@GetMapping("/form")
	public void form(@RequestParam(value = "pno", required = false) String pno,
					 @RequestParam(value = "gno", required = false) String gno, 
					 Model model, GolfVo vo) {
		vo.setGno(gno);
		model.addAttribute("pno", pno);
		model.addAttribute("gno", gno);
		model.addAttribute("m", golfService.edit(vo));
	}
	
	@PostMapping("/insert")
	public String insert(TimeStr time, CourseVo vo, 
						 @RequestParam("courseImg") MultipartFile[] courseImgs) 
			throws Exception, IOException {
		
		// 1) 코스 기본 정보 저장 (코스명, 홀수)
		courseService.insert(vo);
		
		// 2) 1) 에서 저장한 코스번호를 가져옴 
		CourseVo c = courseService.selectCno(vo);
		vo.setCno(c.getCno());
		
		// 3) 코스 이미지 파일 저장
		String fileName = "";
		if (courseImgs != null) {
			for (MultipartFile file : courseImgs) {
				if (!file.isEmpty()) {
					fileName = file.getOriginalFilename(); // 공백 또는 fileName.확장자
					File f = new File(path+fileName);
					System.out.println("===> fileName : "+fileName);
					
					if(f.exists()) { // 폴더에 동일한 파일명이 존재
						String timeStr = time.CurrentTime();
						String onlyFileName = fileName.substring(0,fileName.lastIndexOf("."));
						String ext = fileName.substring(fileName.lastIndexOf("."));
						fileName = onlyFileName +"_" +timeStr +ext;
					}
					file.transferTo(new File(path+fileName)); // 실제 파일 저장
					
				} else {
					fileName = "space.png";
				}
				vo.setCourseImgStr(fileName); // 데이터베이스 파일명 저장
				courseService.insertImg(vo);
			}
		}
		String gno = vo.getGno();
		String pno = vo.getPno();
		return "redirect:/golf/course/form?gno="+gno+"&pno="+pno;
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(required = false, defaultValue = "1") Integer start, 
			   		   @RequestParam(value = "searchcondition", required = false) String searchcondition, 
			   		   @RequestParam(value = "searchkey", required = false) String searchkey, 
			   		   @AuthenticationPrincipal SecurityUser principal, Model model, CourseVo vo) {
		int pageSize = 10;
		int end = start + pageSize-1;
	    vo.setStart(start);
	    vo.setEnd(end);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
	    
        if (searchkey != null) {
			if (!searchkey.isEmpty() && !searchkey.startsWith("%")) {
					searchkey = '%'+searchkey+'%';
					vo.setSearchkey(searchkey);
			}
		}
        model.addAttribute("searchcondition", vo.getSearchcondition());
		model.addAttribute("searchkey", vo.getSearchkey());
		
		int currentPage = start / pageSize + 1;
		int totalRecord = courseService.count(vo);
		int totalPage = (int)(Math.ceil((double)totalRecord / pageSize));
		int listSize = 5;
		int listStartPage = (currentPage-1) / listSize * listSize + 1;
		int listEndPage =  listStartPage + listSize -1;
		model.addAttribute("pageSize", pageSize);
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
		
		String authStr = principal.getAuthorities().toString();
		int index = authStr.lastIndexOf(",");
		String role = authStr.substring(6, index);
		if ("GUEST".equals(role)) {
			System.out.println("===> guest(O) list");
			model.addAttribute("li", courseService.guestList(vo));
		} else {
			System.out.println("===> guest(X) list");
			model.addAttribute("li", courseService.list(vo));
		}
		return "/golf/course/list";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, CourseVo vo) {
		System.out.println("===> edit course vo : "+vo.getCno());
		model.addAttribute("m", courseService.edit(vo));
		model.addAttribute("imgs", courseService.courseImgList(vo)); // 이미지 리스트
		return "/golf/course/edit";
	}
	
}


