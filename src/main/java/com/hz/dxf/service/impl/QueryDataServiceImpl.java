package com.hz.dxf.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hz.dxf.dao.factory.model.TableAttributeModel;
import com.hz.dxf.dao.factory.service.DBOptionService;
import com.hz.dxf.dao.factory.service.QueryDataDao;
import com.hz.dxf.dao.factory.util.StatementUtil;
import com.hz.dxf.service.IQueryDataService;
import com.hz.dxf.util.JsonUtils;
import com.hz.dxf.util.SystemExceiptionUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Desscription : 获取数据
 * @author Blossom
 * @time 2016年12月23日 下午5:55:27
 */
@Service("queryDataService")
public class QueryDataServiceImpl implements IQueryDataService{
	
	private static Logger logger = Logger.getLogger(QueryDataServiceImpl.class);
	
	/**
	 * @Description : 根据条件获取指定属性的数据
	 * @author Blossom
	 * @time 2016年12月23日 下午5:55:38
	 * @see com.hz.dxf.service.IQueryDataService#queryData(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject queryData(JSONObject pJson){
		try {
			if (JsonUtils.checkJSONKey(pJson, "tableName")) {
				logger.error("QueryDataServiceImpl》》》》》》》》queryData====参数不全!");
				return JsonUtils.sealedJSON("status", "0", "message", "参数不全!");
			}
			String tableName = pJson.getString("tableName");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			List<String> arrtibuteList = (List<String>)pJson.get("arrtibutes");
			map.put("attributes", arrtibuteList);
			
			String sql = StatementUtil.sqlFromat(map);
			logger.info("QueryDataServiceImpl》》》》》》》》queryData ?????????? 格式化sql语句===== "+ sql);
			JSONArray dataArray = QueryDataDao.queryData(sql);
			
			return JsonUtils.sealedJSON("status", "1", "message", "","datas", dataArray);
			
		} catch (SystemExceiptionUtils e) {
			logger.error("QueryDataServiceImpl》》》》》》》》queryData==error:" + e.getMessage());
			e.printStackTrace();
			return JsonUtils.sealedExceptionJSON(e);
		} catch (ClassNotFoundException e) {
			logger.error("QueryDataServiceImpl》》》》》》》》queryData==error:" + e.getMessage());
			e.printStackTrace();
			return JsonUtils.sealedExceptionJSON(e);
		} catch (SQLException e) {
			logger.error("QueryDataServiceImpl》》》》》》》》queryData==error:" + e.getMessage());
			e.printStackTrace();
			return JsonUtils.sealedExceptionJSON(e);
		}
	}

	/**
	 * @Description : 获取数据库当中的所有表或视图
	 * @author Blossom
	 * @time 2016年10月17日 下午8:57:38
	 * @see com.hz.dxf.service.IQueryDataService#queryAllTaleOrViewsByDBName(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	public JSONObject queryAllTaleOrViewsByDBName(JSONObject pJson) {

		
		return DBOptionService.getAllTableByDBName(pJson);
	}

	/**
	 * @Description : 根据表或视图名称获取属性详情
	 * @author Blossom
	 * @time 2016年10月17日 下午8:57:38
	 * @see com.hz.dxf.service.IQueryDataService#queryTableMetaDataByTableName(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	public JSONObject queryTableMetaDataByTableName(JSONObject pJson) {

		return DBOptionService.getTableMetaDataByTableName(pJson);
	}

	/* (non-Javadoc)
	 * @see com.jnoos.service.IQueryDataService#queryTableMetaDataAllInfo(net.sf.json.JSONObject)
	 */
	@SuppressWarnings("unchecked")
	public JSONObject queryTableMetaDataAllInfo(JSONObject pJson) {
		try {
			//获取所有的表
			JSONObject json = DBOptionService.getAllTableByDBName(pJson);
			
			List<String> tableList = (List<String>)json.get("tables");
			List<TableAttributeModel> tAttributeList = new ArrayList<TableAttributeModel>();
			
			//循环获取表当中的所有属性
			JSONObject paramJSON = null;
			for (String string : tableList) {
				paramJSON = new JSONObject();
				paramJSON.put("tableName", string);
				TableAttributeModel tableAttributeModel = DBOptionService.getTableMetaData(paramJSON);
				tAttributeList.add(tableAttributeModel);
			}
			
			return JsonUtils.sealedJSON("status", "1", "message", "","tableAttributes", tAttributeList);
		} catch (SystemExceiptionUtils e) {
			logger.error("QueryDataServiceImpl》》》》》》》》queryTableMetaDataAllInfo==error:" + e.getMessage());
			e.printStackTrace();
			return JsonUtils.sealedExceptionJSON(e);
		}
	}

}
