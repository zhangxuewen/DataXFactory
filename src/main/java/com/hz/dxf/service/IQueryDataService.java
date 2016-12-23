package com.hz.dxf.service;

import net.sf.json.JSONObject;

public interface IQueryDataService {

	/**
	 * @Description : 根据条件获取表或视图中的数据
	 * @author Blossom
	 * @time 2016年10月17日 下午8:55:23
	 * @param pJson
	 * @return
	 */
	public JSONObject queryData(JSONObject pJson);
	
	/**
	 * @Description : 获取数据库中所有表或视图
	 * @author Blossom
	 * @time 2016年10月17日 下午8:55:44
	 * @param pJson
	 * @return
	 */
	public JSONObject queryAllTaleOrViewsByDBName(JSONObject pJson);

	/**
	 * @Description : 根据表或视图名获取表属性详情
	 * @author Blossom
	 * @time 2016年10月17日 下午8:57:09
	 * @param pJson
	 * @return
	 */
	public JSONObject queryTableMetaDataByTableName(JSONObject pJson);
	
	/**
	 * @Description : 获取数据库当中所有的表以及表结构信息
	 * @author johb
	 * @time 2016年11月24日 上午11:56:59
	 *
	 */
	public JSONObject queryTableMetaDataAllInfo(JSONObject pJson);
	
}
