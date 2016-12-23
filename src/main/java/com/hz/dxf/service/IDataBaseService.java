package com.hz.dxf.service;

import net.sf.json.JSONObject;

/**
 * @Description : 数据库服务接口
 * @author johb
 * @time 2016年11月17日 上午11:12:40
 */
public interface IDataBaseService {
	
	/**
	 * @Description : 设置数据库连接
	 * @author johb
	 * @time 2016年11月17日 上午11:15:46
	 *
	 */
	public JSONObject setUpDataConnection(JSONObject pJson);
	
	/**
	 * @Description : 获取全部的参数信息
	 * @author johb
	 * @time 2016年11月17日 下午3:21:21
	 *
	 */
	public JSONObject queryDataBaseParam(JSONObject pJson);

	/**
	 * @Description : 修改参数信息
	 * @author johb
	 * @time 2016年11月17日 下午5:17:18
	 *
	 */
	public JSONObject updateDataBaseParam(JSONObject pJson);
	

}
