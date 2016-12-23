package com.hz.dxf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ManageController {

	/***
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView doIndex() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("father", "");
		model.put("children", "index");
		return new ModelAndView("index", model);
	}

	/***
	 * 场景管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "scenes", method = RequestMethod.GET)
	public ModelAndView doScenes() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("father", "screen");
		model.put("children", "scenes");
		return new ModelAndView("scenes", model);
	}

	/***
	 * 大屏布局
	 * 
	 * @return
	 */
	@RequestMapping(value = "layout", method = RequestMethod.GET)
	public ModelAndView doLayout() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("father", "screen");
		model.put("children", "layout");
		return new ModelAndView("layout", model);
	}
	
	/***
	 * 交互日志
	 * 
	 * @return
	 */
	@RequestMapping(value = "interlog", method = RequestMethod.GET)
	public ModelAndView doInterlog() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("father", "screen");
		model.put("children", "interlog");
		return new ModelAndView("interlog", model);
	}

	/***
	 * 创建主题
	 * 
	 * @return
	 */
	@RequestMapping(value = "ctheme", method = RequestMethod.GET)
	public ModelAndView doCtheme() {
		Map<String, String> model = new HashMap<String, String>();
		model.put("father", "rchart");
		model.put("children", "ctheme");
		return new ModelAndView("ctheme", model);
	}
	
	@RequestMapping(value = "map", method = RequestMethod.GET)
	public ModelAndView doMap() {
		return new ModelAndView("map");
	}
	
	@RequestMapping(value = "demo", method = RequestMethod.GET)
	public ModelAndView doDemo() {
		return new ModelAndView("demo");
	}
}
