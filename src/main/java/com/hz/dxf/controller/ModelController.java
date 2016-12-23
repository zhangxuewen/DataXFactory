package com.hz.dxf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hz.dxf.tool.CookieUtil;

@Controller
@RequestMapping("/model")
public class ModelController {
	
	@RequestMapping("/return")
	public String returnModel(HttpServletRequest request,
			HttpServletResponse response,
			Model model){
		CookieUtil cookie = new CookieUtil(request, null, -1);
		String clientID = cookie.getCookieValue("CLIENTID");
		System.out.println("CLIENTID: "+clientID);
		
		request.getSession().setAttribute("cid", "model1");
		request.getSession().setAttribute("name", "");
		
		return "views/manager/model";
	}
	
	@RequestMapping("/one")
	public String returnModelOne(HttpServletRequest request,
			HttpServletResponse response,
			Model model){
		CookieUtil cookie = new CookieUtil(request, null, -1);
		String clientID = cookie.getCookieValue("CLIENTID");
		System.out.println("CLIENTID: "+clientID);
		
		request.getSession().setAttribute("cid", "model1");
		request.getSession().setAttribute("name", "");
		
		return "theme/model_one";
	}

}
