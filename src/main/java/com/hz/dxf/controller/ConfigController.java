package com.hz.dxf.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hz.dxf.service.IDataBaseService;
import com.hz.dxf.service.IQueryDataService;

import net.sf.json.JSONObject;

/**
 * @Desscription : 系统参数配置控制
 * @author Blossom
 * @time 2016年10月17日 下午8:11:59
 */
@Controller
public class ConfigController {

	@Resource(name = "queryDataService")
	private IQueryDataService queryDataService;
	
	@Resource(name = "dataBaseService")
	private IDataBaseService dataBaseService;

	/**
	 * @Description : 进入后台管理首页
	 * @author Blossom
	 * @time 2016年10月17日 下午8:23:39
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/index")
	public String managerIndex(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JSONObject json = new JSONObject();
		json = dataBaseService.queryDataBaseParam(json);
		if (json.getInt("status") == 1) {
			model.addAttribute("dataBaseParams", json.get("dataBaseParams"));
		}
		return "views/manager/index";
		
	}

	/**
	 * @Description : 获取数据
	 * @author Blossom
	 * @time 2016年11月22日 下午9:10:57
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/main/index")
	@ResponseBody
	public JSONObject manager(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		JSONObject json = new JSONObject();
		json = dataBaseService.queryDataBaseParam(json);
		if (json.getInt("status") == 1) {
			model.addAttribute("dataBaseParams", json.get("dataBaseParams"));
		}
		return json;
	}
	
	/**
	 * @Description : 设置或修改接入数据源参数
	 * @author Blossom
	 * @time 2016年10月17日 下午8:23:58
	 * @param dbType
	 * @param dbName
	 * @param address
	 * @param port
	 * @param user
	 * @param password
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/config/setUp")
	public @ResponseBody
	JSONObject setUpConfig(@RequestParam(required=false, defaultValue="") String dbType,
			@RequestParam(required=false, defaultValue="") String dbName, 
			@RequestParam(required=false, defaultValue="") String address,
			@RequestParam(required=false, defaultValue="") String port,
			@RequestParam(required=false, defaultValue="") String user,
			@RequestParam(required=false, defaultValue="") String password, 
			@RequestParam(required=false, defaultValue="") String dpId,
			HttpServletRequest request,
			HttpServletResponse response, 
			Model model) {

		JSONObject json = new JSONObject();
		json.put("dbType", dbType);
		json.put("dbName", dbName);
		json.put("address", address);
		json.put("port", port);
		json.put("user", user);
		json.put("password", password);
		json.put("dpId", dpId);
		
		if ("".equals(dpId)) {
			model.addAttribute("dbType", dbType);
			model.addAttribute("dbName", dbName);
			model.addAttribute("address", address);
			model.addAttribute("port", port);
			model.addAttribute("user", user);
			model.addAttribute("password", password);
		}
		


		json = dataBaseService.setUpDataConnection(json);


		if (json.getInt("status") == 1) {
			//json = queryDataService.queryAllTaleOrViewsByDBName(json);
			json = queryDataService.queryTableMetaDataAllInfo(json);
		} else {
			json.put("status", 0);
		}
		return json;
	}

	@RequestMapping("/config/updateOrDelete")
	@ResponseBody
	public JSONObject updateOrDeleteConfig(@RequestParam(required=false, defaultValue="") String dbType,
			@RequestParam(required=false, defaultValue="") String dbName, 
			@RequestParam(required=false, defaultValue="") String address,
			@RequestParam(required=false, defaultValue="") String port,
			@RequestParam(required=false, defaultValue="") String user,
			@RequestParam(required=false, defaultValue="") String password, 
			@RequestParam(required=false, defaultValue="") String dpId,
			@RequestParam(required=false, defaultValue="1") String status,
			HttpServletRequest request,
			HttpServletResponse response, 
			Model model){
		JSONObject json = new JSONObject();
		json.put("dbType", dbType);
		json.put("dbName", dbName);
		json.put("address", address);
		json.put("port", port);
		json.put("user", user);
		json.put("password", password);
		json.put("status", status);
		json.put("id", dpId);
		
		json = dataBaseService.updateDataBaseParam(json);
		
		return json;
	}
	
	/**
	 * @Description : 根据表或视图名称获取属性详情
	 * @author Blossom
	 * @time 2016年10月17日 下午9:40:55
	 * @param tableName
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/query/attribute")
	public @ResponseBody
	JSONObject queryTableMetaData(@RequestParam() String tableName,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		JSONObject json = new JSONObject();

		json.put("tableName", tableName);

		json = queryDataService.queryTableMetaDataByTableName(json);

		return json;
	}
	
	/**
	 * @Description : 更新数据库连接参数信息
	 * @author Blossom
	 * @time 2016年11月17日 下午9:35:25
	 * @param dbType
	 * @param dbName
	 * @param address
	 * @param port
	 * @param user
	 * @param password
	 * @param dpId
	 * @param status
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/config/update")
	@ResponseBody
	public JSONObject updateDataBase(@RequestParam(required=false, defaultValue="") String dbType,
			@RequestParam(required=false, defaultValue="") String dbName, 
			@RequestParam(required=false, defaultValue="") String address,
			@RequestParam(required=false, defaultValue="") String port,
			@RequestParam(required=false, defaultValue="") String user,
			@RequestParam(required=false, defaultValue="") String password, 
			@RequestParam() String dpId,
			@RequestParam(required=false, defaultValue="1") String status,
			HttpServletRequest request,
			HttpServletResponse response, 
			Model model){
		
		JSONObject json = new JSONObject();
		json.put("dbType", dbType);
		json.put("dbName", dbName);
		json.put("address", address);
		json.put("port", port);
		json.put("user", user);
		json.put("password", password);
		json.put("dpId", dpId);
		json.put("status", status);
		
		json = dataBaseService.updateDataBaseParam(json);
		
		return json;
	}
	
	
}
