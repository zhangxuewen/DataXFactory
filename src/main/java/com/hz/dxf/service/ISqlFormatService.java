package com.hz.dxf.service;

import net.sf.json.JSONObject;

/**
 * @Description : sql动态生成
 * @author johb
 * @time 2016年10月20日 上午10:30:21
 */
public interface ISqlFormatService {
	
	/**
	 * @Description : sql语句生成
	 * @author johb
	 * @time 2016年10月20日 上午10:31:39
	 * @param pJson
	 * @return
	 */
	public JSONObject sqlFormat(JSONObject pJson);
	
	/**
	 * @Description : 构造sql语句
	 * @author johb
	 * @time 2016年11月29日 下午4:11:12
	 *
	 */
	public JSONObject structureSQL(JSONObject pJson);
	
	/**
	 * @Description : 更新或删除sql
	 * @author Blossom
	 * @time 2016年12月5日 下午9:05:37
	 * @param pJson
	 * @return
	 */
	public JSONObject updateOrdeleteSQL(JSONObject pJson);
	
	/**
	 * @Description : 获取sql列表
	 * @author Blossom
	 * @time 2016年12月5日 下午9:06:13
	 * @param pJson
	 * @return
	 */
	public JSONObject querySQL(JSONObject pJson);

}
