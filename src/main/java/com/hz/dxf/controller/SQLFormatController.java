package com.hz.dxf.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hz.dxf.service.ISqlFormatService;

/**
 * @Description : sql格式化控制
 * @author johb
 * @time 2016年10月19日 下午2:00:30
 */
@Controller
@RequestMapping("/sql")
public class SQLFormatController {

	@Resource(name = "sqlFromatService")
	private ISqlFormatService sqlFromatService;
	
	@RequestMapping("/setUp")
	@ResponseBody
	public JSONObject setUpSqlFormat(@RequestParam() String select,
			@RequestParam() String from, @RequestParam() String where,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		JSONObject json = new JSONObject();
		
		json.put("select", select);
		json.put("from", from);
		json.put("where", where);
		
		json = sqlFromatService.sqlFormat(json);
		
		return json;
	}
	
	/**
	 * @Description : SQL语句构造
	 * @author johb
	 * @throws UnsupportedEncodingException 
	 * @time 2016年11月29日 下午4:08:37
	 *
	 */
	@RequestMapping("/create")
	@ResponseBody
	public JSONObject structureSql(HttpServletRequest request,
			HttpServletResponse response,
			Model model) throws UnsupportedEncodingException{
		
		JSONObject json = new JSONObject();
		String params = URLDecoder.decode(request.getParameter("params"),"UTF-8");
		System.out.println("传过来的参数:"+params);
		json.put("params", params);
		
		json = sqlFromatService.structureSQL(json);
		
		return json;
	}
	
	/**
	 * @Description : 获取sql列表
	 * @author Blossom
	 * @time 2016年12月5日 下午9:31:07
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public JSONObject querySqlInfo(@RequestParam(required=false,defaultValue="")String id,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model){
		
		JSONObject json = new JSONObject();
		json.put("id", id);
		
		json = sqlFromatService.querySQL(json);
		
		return json;
	}
	
	/**
	 * @Description : 编辑sql
	 * @author Blossom
	 * @time 2016年12月5日 下午9:30:47
	 * @param id
	 * @param status
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public JSONObject updateOrdelete(@RequestParam() String id,
			@RequestParam(required=false,defaultValue="1") String status,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model){
		
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("status", status);
		
		json = sqlFromatService.updateOrdeleteSQL(json);
		
		return json;
	}

}
