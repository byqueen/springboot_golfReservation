package com.tiny.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tinyego.TimeStr;
import com.tinyego.golf.GolfService;
import com.tinyego.golf.GolfVo;
import com.tinyego.product.ProductService;
import com.tinyego.product.ProductVo;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/golf/product")
public class ProductController {
	ProductController() {
		System.out.println("===> ProductController()");
	}
	
	@Autowired
	GolfService golfservice;
	
	@Autowired
	ProductService prodservice;
	
	@Autowired
	private ServletContext servletContext;
	
	String path = "";
	@PostConstruct
	public void init() {
		path = servletContext.getRealPath("/files/golf/course/");
	}

	@GetMapping("/form")
	public String insertProdForm(@RequestParam("gno") String gno, 
								 Model model, GolfVo vo) {
		System.out.println("===> product form");
		System.out.println("===> gno : "+gno);
		System.out.println("===> vo gno(1) : "+vo.getGno());
		if (vo.getGno().isEmpty()) {
			vo.setGno(gno);
		}
		GolfVo m = golfservice.edit(vo);
		String golfcourseNm = m.getGolfcourseNm();
		String usertype = m.getUsertype();
		String region = m.getRegion();
		String glocation = m.getGlocation();
		String holeNo = m.getHoleNo();
		
		model.addAttribute("gno", gno);
		model.addAttribute("golfcourseNm", golfcourseNm);
		model.addAttribute("usertype", usertype);
		model.addAttribute("region", region);
		model.addAttribute("glocation", glocation);
		model.addAttribute("holeNo", holeNo);
		
		vo.setGno("");
		System.out.println("===> vo gno(2) : "+vo.getGno());
		return "/golf/product/form";
	}
	
	@GetMapping("/list")
	public String listProd(@RequestParam(required = false, defaultValue = "1") Integer start,
						   @RequestParam(value = "searchkey", required = false) String searchkey, 
						   Model model, ProductVo vo) {
		System.out.println("===> golf product list");
		System.out.println("===> searchkey : "+searchkey);
		System.out.println("===> vo : "+vo.getSearchkey());
		
		int pageSize = 10;
		int end = start + pageSize-1;
	    vo.setStart(start);
		vo.setEnd(end);
		System.out.println("===> start / end : "+start+" / "+end);
		
		if (searchkey != null) {
			if (!searchkey.startsWith("%")) {
				searchkey = "%" + searchkey + "%";
				vo.setSearchkey(searchkey);
			} else if (searchkey.startsWith("%")) {
				vo.setSearchkey(searchkey);
			}
		}
		vo.setSearchkey(searchkey);
		
		int currentPage = start / pageSize + 1;
		int totalRecord = prodservice.count(vo);
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
		
		model.addAttribute("li", prodservice.list(vo));
		System.out.println("===============");
		return "/golf/product/list";
	}
	
	@GetMapping("/edit")
	public String editProd(Model model, ProductVo vo) {
		System.out.println("===> golf edit product vo : "+vo.getGno());
		model.addAttribute("m", prodservice.edit(vo));
		return "/golf/product/edit";
	}
	
	@PostMapping("/insert")
	public String insertProd(ProductVo vo, TimeStr time) throws Exception, IOException {
		System.out.println("===> insert golf product gno : "+vo.getGno());
		prodservice.insert(vo);		
		return "redirect:/golf/product/form?gno="+vo.getGno();
	}
	
	@PostMapping("/update")
	public String updateProd(ProductVo vo, TimeStr time) throws Exception, IOException {
		System.out.println("===> update golf product");
		prodservice.update(vo);
		return "redirect:/golf/product/list";
	}
}
