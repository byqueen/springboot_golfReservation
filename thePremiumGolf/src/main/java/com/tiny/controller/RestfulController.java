package com.tiny.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tinyego.golf.GolfVo;
import com.tinyego.restful.RestfulService;

@Controller
@RequestMapping("/restful")
public class RestfulController {

	@Autowired
	RestfulService service;
	
	@GetMapping("/insert")
	public String insertRestful() throws Exception {
		String URL = "https://api.odcloud.kr/api/15118920/v1/uddi:0e5b12d2-1cc8-4caf-ba96-c2c7d1ef8d83";
		String RESULTTYPE = "json";
        String PAGE = "1";
        String PERPAGE = "525";
        String SERVICEKEY = "6vxALldo8qT79RIicc8p3NJSyc4tzsqbJYZLeIcxXxoamvQF1nHks6Xh4wm5TJsoTNa89J0J7raK%2BTy8y96zqw%3D%3D";
        
		StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(PAGE, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode(PERPAGE, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICEKEY);
        
		URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        	rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
        	rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
        	sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb);
        
        JSONParser jsonParser = new JSONParser();
	    JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
	    JSONArray InfoArray = (JSONArray) jsonObject.get("data");
	    
	    for (int i = 0; i < InfoArray.size(); i++) {
	    	System.out.println("=items_"+i+" ===========================================");
	    	
	    	GolfVo m = new GolfVo();
	        JSONObject object = (JSONObject)InfoArray.get(i);                
	        String a1 = String.valueOf(object.get("업소명"));
	        String a2 = String.valueOf(object.get("세부종류"));
	        String a3 = String.valueOf(object.get("지역"));
	        String a4 = String.valueOf(object.get("소재지"));
	        String a5 = String.valueOf(object.get("사업자명(대표자)"));
	        String a6 = String.valueOf(object.get("총면적(제곱미터)"));
	        String a7 = String.valueOf(object.get("홀수(홀)"));
	        m.setGolfcourseNm(a1);
	        m.setUsertype(a2);
	        m.setRegion(a3);
	        m.setGlocation(a4);
	        m.setGown(a5);
	        m.setTotalarea(a6);
	        m.setHoleNo(a7);
	        service.insert(m);
	        System.out.println("성공 ==> "+ i );
	    }
		return "/golf/list";
	}
	
	@GetMapping("/list")
	public String restfulList(Model model) throws Exception {
		String URL = "https://api.odcloud.kr/api/15118920/v1/uddi:0e5b12d2-1cc8-4caf-ba96-c2c7d1ef8d83";
		String RESULTTYPE = "json";
        String PAGE = "1";
        String PERPAGE = "10";
        String SERVICEKEY = "6vxALldo8qT79RIicc8p3NJSyc4tzsqbJYZLeIcxXxoamvQF1nHks6Xh4wm5TJsoTNa89J0J7raK%2BTy8y96zqw%3D%3D";
        
		StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode(RESULTTYPE, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(PAGE, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode(PERPAGE, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICEKEY);
		System.out.println("===> "+urlBuilder.toString());
	
		URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        	rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
        	rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
        	sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb);
        
        try {JSONParser jsonParser = new JSONParser();
            // JSON 데이터를 넣어 JSON Object 로 만듦
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            System.out.println("===> jsonObject : "+jsonObject);
            
            // items 배열로 추출함
            JSONArray InfoArray = (JSONArray) jsonObject.get("data");
            System.out.println("===> InfoArray : "+InfoArray);
            
            List<GolfVo> li = new ArrayList<GolfVo>();
            for (int i = 0; i < InfoArray.size(); i++) {
            	System.out.println("=items_"+i+" ===========================================");
            	GolfVo m = new GolfVo();
            	// 배열 안에 있는것도 JSON 형식이므로 JSON Object 로 추출함
                JSONObject object = (JSONObject)InfoArray.get(i);                
                String a1 = String.valueOf(object.get("업소명"));
                System.out.println("===> a1 : "+a1);
                
                String a2 = String.valueOf(object.get("세부종류"));
                String a3 = String.valueOf(object.get("지역"));
                String a4 = String.valueOf(object.get("소재지"));
                String a5 = String.valueOf(object.get("사업자명(대표자)"));
                String a6 = String.valueOf(object.get("총면적(제곱미터)"));
                String a7 = String.valueOf(object.get("홀수(홀)"));
                m.setGolfcourseNm(a1);
                m.setUsertype(a2);
                m.setRegion(a3);
                m.setGlocation(a4);
                m.setGown(a5);
                m.setTotalarea(a6);
                m.setHoleNo(a7);
                li.add(m);
                System.out.println("성공 ==> "+ i );
            }
            model.addAttribute("li", li);
            
            String currentCount = String.valueOf(jsonObject.get("currentCount"));
            model.addAttribute("currentCount", currentCount);
            return "/golf/list";
   
        } catch (Exception e) {
        	e.printStackTrace();
        	return "{}";

        }
	}
}
