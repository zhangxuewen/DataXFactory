package com.hz.dxf.dao.factory.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hz.dxf.dao.factory.config.ConfigModel;
import com.hz.dxf.dao.factory.util.DBUtils;
import com.hz.dxf.dao.factory.util.ResultSetUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.CycleDetectionStrategy;

public class QueryDataDao {

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;

	/**
	 * 获取数据
	 * 
	 * @author johb
	 * @time 2016年10月14日
	 * @param pSql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static JSONArray queryData(String pSql) throws ClassNotFoundException, SQLException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonBeanProcessor(java.sql.Date.class, new JsDateJsonBeanProcessor());

		connection = DBUtils.getConnection(ConfigModel.getDataBaseParamModel());
		statement = connection.createStatement();
		resultSet = statement.executeQuery(pSql);
		JSONArray jsonArray = ResultSetUtil.resultSetToJsonArray(resultSet);

		DBUtils.close(resultSet, statement, connection);

		return jsonArray;
	}

}
