package com.tiny.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinyego.chatgpt.ChatgptService;
import com.tinyego.chatgpt.JsonResponse;

@Controller
@RequestMapping("/api/chat")
public class ChatgptController {
	ChatgptController() {
		System.out.println("===> ChatGptController()");
	}
	
    @Autowired
    private ChatgptService chatGptService;

    @PostMapping("/ask")
    public String askChatGpt(String prompt, Model model) 
    		throws Exception, DatabindException, IOException {
    	System.out.println("prompt(1) : "+prompt); // 폼에서 넣은 값
	        	
    	String str = chatGptService.getResponse(prompt); // 리턴값이 JSON
    	System.out.println("prompt(2) : "+str);

    	ObjectMapper objectMapper = new ObjectMapper();
    	JsonResponse jsonResponse = objectMapper.readValue(str, JsonResponse.class); // Vo 클래스
    	String content = jsonResponse.getChoices()[0].getMessage().getContent(); // 하위의 Content를 가져옴
    	model.addAttribute("result", content);
          
    	// redirect 로 값을 넘길 땐, URL 쿼리 파라미터를 통해 데이터를 전달해야 함
        return "redirect:/index?result="+URLEncoder.encode(content, StandardCharsets.UTF_8);
    }
}
