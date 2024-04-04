package com.example.reactProject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.reactProject.entity.React;
import com.example.reactProject.service.ReactService;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController				// rendering 하지 않고, 데이터를 보내는 컨트롤러 (@ResponseBody 느낌)
@RequestMapping("/react")
@RequiredArgsConstructor
public class ReactController {
	private final ReactService reactService;
	
	@GetMapping("/data")
	public String data() {
		return "스프링부트에서 보낸 데이터";
	}
	
	@GetMapping("/json")
	public String json() {
		JSONObject jObj = new JSONObject();
		jObj.put("uid", "james");
		jObj.put("uname", "제임스");
		return jObj.toString();
	}
	
	@PostMapping("/form")
	public String form(String uid, String uname) {
		System.out.println("uid= " + uid + "uname= " + uname);
		return "";
	}
	
	@PostMapping("/multi")
	public String form(String uid, String uname, MultipartFile file) {
		String msg = "uid=" + uid + ", uname=" + uname + ", fname=" + file.getOriginalFilename();
		System.out.println(msg);

		return msg;
	}
	
	@GetMapping("/list")
	public JSONArray List(@PathVariable(required=false) Integer page) {
		page = (page == null) ? 1 : page;
		int totalUserCount = reactService.getUserCount();
		int totalPages = (int) Math.ceil(totalUserCount / (double) reactService.COUNT_PER_PAGE);
		int startPage = (int) Math.ceil((page - 0.5) / reactService.COUNT_PER_PAGE - 1) * reactService.COUNT_PER_PAGE + 1;
		int endPage = Math.min(totalPages, startPage + reactService.COUNT_PER_PAGE - 1);
		List<Integer> pageList = new ArrayList<>();
		for (int i = startPage; i <= endPage; i++)
			pageList.add(i);
		
//		jObj.put("currentUserPage", page);
//		jObj.put("totalPages", totalPages);
//		jObj.put("endPage", endPage);
//		jObj.put("pageList", pageList);
		
		JSONArray jArr = new JSONArray();
		List<React> list = reactService.getUserList(page);
		for(React user : list) {
			JSONObject jObj = new JSONObject();
			jObj.put("uid", user.getUid());
			jObj.put("uname", user.getUname());
			jObj.put("email", user.getEmail());
			jArr.add(jObj);
		}
		
		return jArr;
	}
}
