package com.hz.dxf.dao.factory.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.hz.dxf.dao.factory.config.ConfigModel;
import com.hz.dxf.dao.factory.model.AttributeDetailsModel;
import com.hz.dxf.dao.factory.model.TableAttributeModel;
import com.hz.dxf.dao.factory.util.DBUtils;

/**
 * 数据库结构操作
 * 
 * @author johb
 * @time 2016年10月13日
 */
public class DBOptionService {

	private static Logger logger = Logger.getLogger(DBOptionService.class);
	private static Connection connection = null;
	@SuppressWarnings("unused")
	private static Statement stmtStatement = null;
	private static ResultSet resultSet = null;

	/**
	 * 根据数据库名获取全部表或视图
	 * 
	 * @author johb
	 * @time 2016年10月13日
	 * @param pJson
	 * @return
	 */
	public static JSONObject getAllTableByDBName(JSONObject pJson) {

		logger.info("获取数据库下所有表或视图=======DBOptionService>>>>>>>getAllTableByDBName");
		JSONObject resultJSON = new JSONObject();
		try {
			connection = DBUtils.getConnection(ConfigModel.getDataBaseParamModel());
			DatabaseMetaData data = connection.getMetaData();
			resultSet = data.getTables(null, null, null, new String[] { "TABLE" });
			List<String> tableList = new ArrayList<String>();
			while (resultSet.next()) {
				tableList.add(resultSet.getString(3));
			}
			resultJSON.put("tables", tableList);
			resultJSON.put("status", 1);
			resultJSON.put("message", 0);

		} catch (ClassNotFoundException e) {
			logger.error("DBOptionService>>>>>getAllTableByDBName=====" + e.getMessage());
			resultJSON.put("status", 0);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("DBOptionService>>>>>getAllTableByDBName=====" + e.getMessage());
			resultJSON.put("status", 0);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtils.close(resultSet, null, connection);
		}
		return resultJSON;
	}

	/**
	 * 获取表所有属性名称
	 * 
	 * @author johb
	 * @time 2016年10月13日
	 * @param pJson
	 * @return
	 */
	public static JSONObject getTableMetaDataByTableName(JSONObject pJson) {
		logger.info("获取表中所有属性=========DBOptionService>>>>>>>getTableMetaDataByTableName");
		JSONObject resultJSON = new JSONObject();

		try {
			String tableName = pJson.getString("tableName");
			if (null == tableName || "".equals(tableName)) {
				logger.error("DBOptionService>>>>>>>getTableMetaDataByTableName method tableName parament not null");
				resultJSON.put("status", 0);
				resultJSON.put("message", "参数不全!");
				return resultJSON;
			}
			Connection connection = DBUtils.getConnection(ConfigModel.getDataBaseParamModel());
			DatabaseMetaData data = connection.getMetaData();
			//主键
			ResultSet keyResultSet = data.getPrimaryKeys(null, null, tableName);
			StringBuilder keyBuilder = new StringBuilder();
			while (keyResultSet.next()) {
				keyBuilder.append(keyResultSet.getString(4)).append(",");
			}
			//外键关联
			ResultSet exportedKeySet = data.getExportedKeys(null, null, tableName);
			List<String> exportedKeyList = new ArrayList<String>();
			while (exportedKeySet.next()) {
				exportedKeyList.add(exportedKeySet.getString(7));
			}
			//属性
			ResultSet resultSet = data.getColumns(null, "%", tableName, "%");
			List<AttributeDetailsModel> modelList = new ArrayList<AttributeDetailsModel>();
			while (resultSet.next()) {
				String columnName = resultSet.getString("COLUMN_NAME");
				String columnType = resultSet.getString("TYPE_NAME");
				int datasize = resultSet.getInt("COLUMN_SIZE");
				int digits = resultSet.getInt("DECIMAL_DIGITS");
				int nullable = resultSet.getInt("NULLABLE");
				String description = resultSet.getString(12);
				AttributeDetailsModel model = new AttributeDetailsModel();

				model.setPrimaryKey(keyBuilder.toString().contains(columnName));
				model.setColumnName(columnName);
				model.setColumnType(columnType);
				model.setDatasize(datasize);
				model.setDigits(digits);
				model.setNullable(nullable);
				model.setDescription(description);

				modelList.add(model);
			}
			
			TableAttributeModel tableAttributeModel = new TableAttributeModel();
			tableAttributeModel.setAttributeDetails(modelList);
			tableAttributeModel.setExportedKeysTables(exportedKeyList);
			tableAttributeModel.setTableName(tableName);
			
			resultJSON.put("tableAttribute", tableAttributeModel);
			resultJSON.put("models", modelList);
			resultJSON.put("status", 1);
			resultJSON.put("message", "");

		} catch (ClassNotFoundException e) {
			logger.error("DBOptionService>>>>>getTableMetaDataByTableName=====" + e.getMessage());
			resultJSON.put("status", 0);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("DBOptionService>>>>>getTableMetaDataByTableName=====" + e.getMessage());
			resultJSON.put("status", 0);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		}
		return resultJSON;
	}
	
	/**
	 * @Description : 获取表的详细信息
	 * @author johb
	 * @time 2016年11月24日 下午2:19:40
	 *
	 */
	public static TableAttributeModel getTableMetaData(JSONObject pJson) {
		logger.info("获取表中所有属性=========DBOptionService>>>>>>>getTableMetaDataByTableName");
		JSONObject resultJSON = new JSONObject();

		try {
			String tableName = pJson.getString("tableName");
			if (null == tableName || "".equals(tableName)) {
				logger.error("DBOptionService>>>>>>>getTableMetaDataByTableName method tableName parament not null");
				resultJSON.put("status", 0);
				resultJSON.put("message", "参数不全!");
				return null;
			}
			Connection connection = DBUtils.getConnection(ConfigModel.getDataBaseParamModel());
			DatabaseMetaData data = connection.getMetaData();
			//主键
			ResultSet keyResultSet = data.getPrimaryKeys(null, null, tableName);
			StringBuilder keyBuilder = new StringBuilder();
			while (keyResultSet.next()) {
				keyBuilder.append(keyResultSet.getString(4)).append(",");
			}
			//外键关联
			ResultSet exportedKeySet = data.getExportedKeys(null, null, tableName);
			List<String> exportedKeyList = new ArrayList<String>();
			while (exportedKeySet.next()) {
				exportedKeyList.add(exportedKeySet.getString(7));
				
			}
			ResultSet importKeySet = data.getImportedKeys(null, null, tableName);
			StringBuilder exportedKeyBuilder = new StringBuilder();
			while (importKeySet.next()) {
				exportedKeyBuilder.append(importKeySet.getString(8)).append(",");
			}
			
			//属性
			ResultSet resultSet = data.getColumns(null, "%", tableName, "%");
			List<AttributeDetailsModel> modelList = new ArrayList<AttributeDetailsModel>();
			while (resultSet.next()) {
				String columnName = resultSet.getString("COLUMN_NAME");
				String columnType = resultSet.getString("TYPE_NAME");
				int datasize = resultSet.getInt("COLUMN_SIZE");
				int digits = resultSet.getInt("DECIMAL_DIGITS");
				int nullable = resultSet.getInt("NULLABLE");
				String description = resultSet.getString(12);
				AttributeDetailsModel model = new AttributeDetailsModel();
				
				model.setPrimaryKey(keyBuilder.toString().contains(columnName));
				model.setColumnName(columnName);
				model.setColumnType(columnType);
				model.setDatasize(datasize);
				model.setDigits(digits);
				model.setNullable(nullable);
				model.setDescription(description);
				model.setExportedKey(exportedKeyBuilder.toString().contains(columnName));

				modelList.add(model);
			}
			
			TableAttributeModel tableAttributeModel = new TableAttributeModel();
			tableAttributeModel.setAttributeDetails(modelList);
			tableAttributeModel.setExportedKeysTables(exportedKeyList);
			tableAttributeModel.setTableName(tableName);
			
			resultJSON.put("tableAttribute", tableAttributeModel);
			resultJSON.put("models", modelList);
			resultJSON.put("status", 1);
			resultJSON.put("message", "");
			return tableAttributeModel;

		} catch (ClassNotFoundException e) {
			logger.error("DBOptionService>>>>>getTableMetaDataByTableName====="
					+ e.getMessage());
			resultJSON.put("status", 0);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("DBOptionService>>>>>getTableMetaDataByTableName====="
					+ e.getMessage());
			resultJSON.put("status", 0);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}


}
