package com.tiny.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tinyego.golf.GolfService;
import com.tinyego.golf.GolfVo;
import com.tinyego.index.IndexService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/golf")
public class GolfController {
	GolfController() {
		System.out.println("===> GolfController()");
	}
	
	@Autowired
	private GolfService service;
	
	@Autowired
	private ServletContext servletContext;
	
	String path = "";
	@PostConstruct
	public void init() {
		path = servletContext.getRealPath("/files/golf/main/");
	}
	
	@GetMapping("/insertForm")
	public void insertform() {
		System.out.println("===> insert mapping");
		//file = new File(null)
		//golfImg
	}
	
	@GetMapping("/index")
	public String index(Model model) { 
		System.out.println("===> golf index mapping");
		model.addAttribute("li", service.listAll());
		System.out.println("===> li : "+service.listAll());
		return "/golf/index";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(required = false, defaultValue = "1") Integer start, 
					   @RequestParam(value = "usertype", required = false, defaultValue = "전체") String usertype, 
					   @RequestParam(value = "searchkey", required = false) String searchkey, 
					   HttpServletRequest request, Model model, GolfVo vo) {
		System.out.println("===> [param] 1. start / usertype / searchkey : "+start+" / "+usertype+" / "+searchkey);
		System.out.println("===> [vo] 1. start / usertype / searchkey : "+vo.getStart()+" / "+vo.getUsertype()+" / "+vo.getSearchkey());
        
		int pageSize = 10;
		int end = start + pageSize-1;
	    vo.setStart(start);
		vo.setEnd(end);
		System.out.println("===> start / end : "+start+" / "+end);
		
		// radio button
		if (usertype == null || usertype.isEmpty()) {
			System.out.println("===> 1 ");
	        usertype = "전체";
	    }
	    vo.setUsertype("전체".equals(usertype) ? "" : usertype);
	    System.out.println("===> param usertype : " + usertype);
		System.out.println("===> vo. usertype : " + vo.getUsertype());

		// searchbox
		if (searchkey != null && !searchkey.isEmpty() && !searchkey.equals("null")) {
			if (!searchkey.startsWith("%")) {
				searchkey = '%' + searchkey + '%';
		    }
			vo.setSearchkey(searchkey);
		} else {
			vo.setSearchkey("");
	    }
		System.out.println("===> vo. searchkey : " + vo.getSearchkey());
        
		int currentPage = start / pageSize + 1;
		int totalRecord = service.count(vo);
		System.out.println("===> totalRecord : "+totalRecord);
		int totalPage = (int)(Math.ceil((double)totalRecord / pageSize));
		int listSize = 5;
		int listStartPage = (currentPage-1) / listSize * listSize + 1;
		int listEndPage =  listStartPage + listSize -1;

		model.addAttribute("usertype", vo.getUsertype());
		model.addAttribute("searchkey", vo.getSearchkey());
		
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listSize", listSize);
		model.addAttribute("listStartPage", listStartPage);
		model.addAttribute("listEndPage", listEndPage);

		int prePage = (pageSize * listSize) * ((listStartPage - listSize - 1) / listSize) + 1;
		int nextPage = (pageSize * listSize) * (listEndPage / listSize) + 1;
		int lastPage = (totalPage-1) * pageSize + 1;
		int nextPageEnd = (totalPage / listSize) * listSize;
		model.addAttribute("prePage", prePage);
		model.addAttribute("nextPage", nextPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("nextPageEnd", nextPageEnd);
		
        model.addAttribute("li", service.list(vo));
        System.out.println("===> li : "+service.list(vo));
        System.out.println("==============================");
		return "/golf/list";
	}
	
	@GetMapping("/edit")
	public String edit(Model model, GolfVo vo) {
		System.out.println("===> edit vo : "+service.edit(vo));
		model.addAttribute("m", service.edit(vo));
		return "/golf/edit";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest requset, GolfVo vo) 
			throws Exception, IOException {
		System.out.println("===> golf update vo(1) : " + vo);
		System.out.println("===> file path : "+path);
		
		long time = System.currentTimeMillis();
		SimpleDateFormat daytime = new SimpleDateFormat("yyMMddHHmmss");
		String timeStr = daytime.format(time);
		//System.out.println("===> timeStr : "+timeStr);
		
		MultipartFile file = vo.getGolfImg();
		String fileName = file.getOriginalFilename(); // 공백 또는 fileName.확장자
		File f = new File(path+fileName);
		System.out.println("===> fileName : "+fileName);
		
		if (!file.isEmpty()) { // 파일을 첨부했고
			if(f.exists()) { // 폴더에 해당 파일이 존재하면
				String onlyFileName = fileName.substring(0,fileName.lastIndexOf("."));
				String ext = fileName.substring(fileName.lastIndexOf("."));
				fileName = onlyFileName +"_" +timeStr +ext;
			}
			// 실제 파일 저장
			file.transferTo(new File(path+fileName));
		} else {
			fileName = "space.png";
		}
		vo.setGolfImgStr(fileName);
		System.out.println("===> golf update vo(2) : " + vo);
		service.update(vo);
		return "redirect:/golf/list";
	}
	
}
