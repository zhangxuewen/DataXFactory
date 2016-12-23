package com.hz.dxf.dao;

import java.util.List;
import java.util.Map;

import com.hz.dxf.dao.model.DataBaseParamModel;

/**
 * @Description : 数据库参数设置Dao
 * @author johb
 * @time 2016年11月17日 下午1:28:35
 */
public interface DataBaseParamMapper {
	
	/**
	 * @Description : 添加数据库参数
	 * @author johb
	 * @time 2016年11月17日 下午1:29:37
	 *
	 */
	public int saveDataBaseParam(DataBaseParamModel dParamModel);
	
	/**
	 * @Description : 校验数据源信息
	 * @author Blossom
	 * @time 2016年11月26日 下午9:48:45
	 * @param pMap
	 * @return
	 */
	public List<DataBaseParamModel> checkDataBaseParam(Map<String, String> pMap);
	
	/**
	 * @Description : 获取全部的参数信息
	 * @author johb
	 * @time 2016年11月17日 下午1:30:46
	 *
	 */
	public List<DataBaseParamModel> queryDataBaseParam(Map<String, String> pMap);
	
	/**
	 * @Description : 更新参数信息
	 * @author johb
	 * @time 2016年11月17日 下午1:32:17
	 *
	 */
	public int updateDataBaseParam(DataBaseParamModel dParamModel);
	
	/**
	 * @Description : 删除参数信息
	 * @author johb
	 * @time 2016年11月17日 下午1:33:14
	 *
	 */
	public int deleteDataBaseParam(Map<String, String> pMap);

}
