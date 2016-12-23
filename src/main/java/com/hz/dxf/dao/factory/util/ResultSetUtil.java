package com.hz.dxf.dao.factory.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.lang.NullArgumentException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 结果集解析工具类
 * @author johb
 * @time 2016年10月14日
 */
public class ResultSetUtil {

	/**
	 * 解析ResultSet结果集
	 * @author johb
	 * @time 2016年10月14日
	 * @param pResultSet
	 * @return
	 * @throws SQLException
	 */
	public static JSONArray resultSetToJsonArray(ResultSet pResultSet) throws SQLException{
		JSONArray resultArray = new JSONArray();
		if (null == pResultSet) {
			throw new NullArgumentException("参数不全!");
		}
		ResultSetMetaData data = pResultSet.getMetaData();
		int columnCount = data.getColumnCount();
		while (pResultSet.next()) {
			JSONObject json = new JSONObject();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = data.getColumnLabel(i);
				String value = pResultSet.getString(columnName);
				json.put(columnName, value);
			}
			resultArray.add(json);
		}
		return resultArray;
	}
	
}
