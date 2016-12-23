package com.hz.dxf.dao;

import java.util.List;
import java.util.Map;

import com.hz.dxf.model.SqlFormatModel;

/**
 * @Description : sqlDao接口
 * @author johb
 * @time 2016年12月2日 下午3:03:05
 */
public interface SqlFormatMapper {
	
	/**
	 * @Description : 添加sql
	 * @author johb
	 * @time 2016年12月2日 下午3:03:34
	 *
	 */
	public int saveSqlFormat(SqlFormatModel sql);
	
	/**
	 * @Description : 获取sql列表
	 * @author johb
	 * @time 2016年12月2日 下午3:04:15
	 *
	 */
	public List<SqlFormatModel> querySqlFormat(Map<String, String> pMap);
	
	/**
	 * @Description : 更新sql信息
	 * @author johb
	 * @time 2016年12月2日 下午3:04:46
	 *
	 */
	public int updateSqlFormat(Map<String, String> pMap);

}
