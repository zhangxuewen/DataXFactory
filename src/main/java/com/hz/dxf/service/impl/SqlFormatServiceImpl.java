package com.hz.dxf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.dxf.dao.SqlFormatMapper;
import com.hz.dxf.dao.factory.util.StatementUtil;
import com.hz.dxf.dao.factory.util.StatementUtil_02;
import com.hz.dxf.model.SqlFormatModel;
import com.hz.dxf.service.ISqlFormatService;
import com.hz.dxf.util.DateFormatUtil;
import com.hz.dxf.util.Md5;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description : sql语句动态生成
 * @author johb
 * @time 2016年10月20日 上午10:32:14
 */
@Service("sqlFromatService")
public class SqlFormatServiceImpl implements ISqlFormatService {

	private static Logger logger = Logger.getLogger(SqlFormatServiceImpl.class);
	
	@Autowired
	private SqlFormatMapper sqlFormatMapper;
	
	/** 
	 * @author johb
	 * @time 2016年10月20日 上午10:32:15
	 * @see com.hz.dxf.service.ISqlFormatService#sqlFormat(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	public JSONObject sqlFormat(JSONObject pJson) {
		JSONObject resultJSON = new JSONObject();
		try {
			logger.info("SqlFormatServiceImpl》》》》》sqlFormat:"+pJson.toString());
			if (!pJson.containsKey("select") || null == pJson.getString("select") || "".equals(pJson.getString("select"))
					|| !pJson.containsKey("from") || null == pJson.getString("from") || "".equals(pJson.getString("from"))
					|| !pJson.containsKey("where") || null == pJson.getString("where") || "".equals(pJson.getString("where"))) {
				resultJSON.put("status", 0);
				resultJSON.put("message", "参数不全!");
				return resultJSON;
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			//select部分
			String strSelect = pJson.getString("select");
			List<String> selectList = new ArrayList<String>();
			String[] selects= strSelect.split(";");
			for(int i=0 ;i<selects.length;i++){
				selectList.add(selects[i]);
			}
			paramMap.put("attributes", selectList);
			
			//from部分
			String strFrom = pJson.getString("from");
			List<String> fromList = new ArrayList<String>();
			String[] froms= strFrom.split(";");
			for(int i=0 ;i<froms.length;i++){
				fromList.add(froms[i]);
			}
			paramMap.put("from", fromList);
			
			//where部分
			String strWhere = pJson.getString("where");
			List<Map<String, String>> whereList = new ArrayList<Map<String, String>>();
			Map<String, String> whereMap = new HashMap<String, String>();
			String[] wheres = strWhere.split(";");
			for (int i = 0; i < wheres.length; i++) {
				whereMap.put(wheres[i].split("=")[0], wheres[i].split("=")[1]);
			}
			whereList.add(whereMap);
			paramMap.put("where", whereList);
			
			String sql = StatementUtil.sqlFromatByManyTable(paramMap);
			
			logger.info("SqlFormatServiceImpl》》》》》sqlFormat:sql= \n"+sql);
			
			resultJSON.put("sql", sql);
			resultJSON.put("status", 1);
			resultJSON.put("message", "");
			
		} catch (Exception e) {
			logger.error("SqlFormatServiceImpl》》》》》sqlFormat"+e.getMessage());
			resultJSON.put("status", 0);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		}
		return resultJSON;
	}

	/* (non-Javadoc)
	 * @see com.jnoos.service.ISqlFormatService#structureSQL(net.sf.json.JSONObject)
	 */
	@SuppressWarnings({ "deprecation", "rawtypes", "unused" })
	public JSONObject structureSQL(JSONObject pJson) {
		JSONObject resultJSON = new JSONObject();
		try {
			if (!pJson.containsKey("params") || null == pJson.getString("params") || "".equals(pJson.getString("params"))) {
				logger.error("SqlFormatServiceImpl》》》》》structureSQL: 参数不全");
				resultJSON.put("status", 0);
				resultJSON.put("message", "参数不全!");
				return resultJSON;
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			JSONObject json = JSONObject.fromObject(pJson.get("params"));
			if (!json.containsKey("select") || null == json.getString("select") || "".equals(json.getString("select"))
					|| !json.containsKey("from") || null == json.getString("from") || "".equals(json.getString("from")) ) {
				logger.error("SqlFormatServiceImpl》》》》》structureSQL: 参数不全");
				resultJSON.put("status", 0);
				resultJSON.put("message", "参数不全!");
				return resultJSON;
			}
			//select部分
			//JSONArray.fromObject(json.get("select"));
			List selectList = JSONArray.toList(JSONArray.fromObject(json.get("select")));
			paramMap.put("select", selectList);
			
			//from部分
			JSONObject fromJSON = JSONObject.fromObject(json.get("from"));
			Map<String, Object> fromMap = new HashMap<String, Object>();
			if (fromJSON.containsKey("across") && null != fromJSON.getString("across") && !"".equals(fromJSON.getString("across"))) {
				List acrossList = JSONArray.toList(JSONArray.fromObject(fromJSON.get("across")));
				fromMap.put("across", acrossList);
			}
			
			if (fromJSON.containsKey("left") && null != fromJSON.getString("left") && !"".equals(fromJSON.getString("left"))) {
				JSONObject leftJSON = JSONObject.fromObject(fromJSON.get("left"));
				//将json转换为map
				Map<String, String> leftMap = jsonToMap(leftJSON);
				fromMap.put("left", leftMap);
			}
			if (fromJSON.containsKey("right") && null != fromJSON.getString("right") && !"".equals(fromJSON.getString("right"))) {
				JSONObject rightJSON = JSONObject.fromObject(fromJSON.get("right"));
				//将json转换为map
				Map<String, String> rightMap = jsonToMap(rightJSON);
				fromMap.put("right", rightMap);
			}
			if (fromJSON.containsKey("inner") && null != fromJSON.getString("inner") && !"".equals(fromJSON.getString("inner"))) {
				JSONObject innerJSON = JSONObject.fromObject(fromJSON.get("inner"));
				//将json转换为map
				Map<String, String> innerMap = jsonToMap(innerJSON);
				fromMap.put("inner", innerMap);
			}
			if (fromJSON.containsKey("full") && null != fromJSON.getString("full") && !"".equals(fromJSON.getString("full"))) {
				JSONObject fullJSON = JSONObject.fromObject(fromJSON.get("full"));
				//将json转换为map
				Map<String, String> fullMap = jsonToMap(fullJSON);
				fromMap.put("full", fullMap);
			}
			
			paramMap.put("from", fromMap);
			
			
			//where部分
			if (json.containsKey("where") && null != json.getString("where") && !"".equals(json.getString("where"))) {
				//将json转换为List类型
				Map<String, String> whereMap = new HashMap<String, String>();
				List whereList = JSONArray.toList(JSONArray.fromObject(json.get("where")));
				for (Object object : whereList) {
					if (null != object) {
						String whereStr = object.toString();
						String[] wheres = whereStr.split(",");
						whereMap.put(wheres[0], wheres[1]);
					}
				}
				
				paramMap.put("where", whereMap);
			}
			
			
			//group部分
			if (json.containsKey("group") && null != json.getString("group") && !"".equals(json.getString("group"))) {
				//将json类型转换为List类型
				Map<String, String> groupMap = new HashMap<String, String>();
				List groupList = JSONArray.toList(JSONArray.fromObject(json.get("group")));
				for (Object object : groupList) {
					if (null != object) {
						String whereStr = object.toString();
						String[] wheres = whereStr.split("=");
						groupMap.put(wheres[0], wheres[1]);
					}
				}
				paramMap.put("groups", groupMap);
			}
			
			
			//having部分
			if (json.containsKey("having") && null != json.getString("having") && !"".equals(json.getString("having"))) {
				
				List havingList = JSONArray.toList(JSONArray.fromObject(json.get("having")));
				
				paramMap.put("havings", havingList);
				
			}
			
			//order by 部分
			if (json.containsKey("order") && null != json.getString("order") && !"".equals(json.getString("order"))) {
				//将json转换为ListMap类型
				List<Map<String, String>> orderList = new ArrayList<Map<String,String>>();
				List groupList = JSONArray.toList(JSONArray.fromObject(json.get("order")));
				for (Object object : groupList) {
					if (null != object) {
						Map<String, String> map = new HashMap<String, String>();
						String orderStr= object.toString();
						String[] orders = orderStr.split("=");
						map.put(orders[0], orders[1]);
						orderList.add(map);
					}
				}
				paramMap.put("orders", orderList);
				
			}
			
			System.out.println("paramMap===="+paramMap.toString());
			String sql = StatementUtil_02.complexSqlFromatByManyTable(paramMap);
			
			SqlFormatModel sModel = new SqlFormatModel();
			sModel.setCreateTime(DateFormatUtil.getNow());
			sModel.setId(Md5.MD5(System.nanoTime()+"", 32)[1]);
			sModel.setSqlFormat(sql);
			sModel.setStatus(1);
			
			int intTag = sqlFormatMapper.saveSqlFormat(sModel);
		
			
			resultJSON.put("sql", sql);
			resultJSON.put("status", 1);
			resultJSON.put("message", "");
			
			
		} catch (Exception e) {
			logger.error("SqlFormatServiceImpl》》》》》structureSQL: error="+e.getMessage());
			resultJSON.put("status", 0);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		}
		return resultJSON;
	}

	/**
	 * @Description : 将json转换为map
	 * @author johb
	 * @time 2016年11月30日 上午10:21:16
	 *
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> jsonToMap(JSONObject pJSON){
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> iterator = pJSON.keys();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = pJSON.getString(key);
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * @Description : 将json转换为listMap
	 * @author johb
	 * @time 2016年11月30日 上午10:23:41
	 *
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private List<Map<String, String>> jsonToListMap(JSONObject pJSON){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Iterator<String> iterator = pJSON.keys();
		while (iterator.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			String key = iterator.next();
			String value = pJSON.getString(key);
			map.put(key, value);
			list.add(map);
		}
		
		return list;
	}

	/**
	 * @Description : 
	 * @author Blossom
	 * @time 2016年12月5日 下午9:07:05
	 * @see com.hz.dxf.service.ISqlFormatService#updateOrdeleteSQL(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	public JSONObject updateOrdeleteSQL(JSONObject pJson) {
		JSONObject resultJSON = new JSONObject();
		try {
			if (!pJson.containsKey("id") || null == pJson.getString("id") || "".equals(pJson.getString("id"))) {
				resultJSON.put("status", 0);
				resultJSON.put("message", "参数不全");
				return resultJSON;
			}
			String status = "1";
			if (pJson.containsKey("status")) {
				status = pJson.getString("status");
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", status);
			map.put("createTime", DateFormatUtil.getNow());
			
			int intTag = sqlFormatMapper.updateSqlFormat(map);
			if (intTag == 0) {
				resultJSON.put("status", 0);
				resultJSON.put("message", "删除失败");
				return resultJSON;
			}
			
			resultJSON.put("status", 1);
			resultJSON.put("message", "");
			
		} catch (Exception e) {
			resultJSON.put("status", -1);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		}
		return resultJSON;
	}

	/**
	 * @Description : 
	 * @author Blossom
	 * @time 2016年12月5日 下午9:07:05
	 * @see com.hz.dxf.service.ISqlFormatService#querySQL(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	public JSONObject querySQL(JSONObject pJson) {
		JSONObject resultJSON = new JSONObject();
		try {
			String id = "";
			if (pJson.containsKey("id") && null != pJson.getString("id") && !"".equals(pJson.getString("id"))) {
				id = pJson.getString("id");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			
			List<SqlFormatModel> sqlFormatList = sqlFormatMapper.querySqlFormat(map);
			
			resultJSON.put("sqlFormats", sqlFormatList);
			resultJSON.put("status", 1);
			resultJSON.put("message", "");
			
		} catch (Exception e) {
			resultJSON.put("status", -1);
			resultJSON.put("message", e.getMessage());
			e.printStackTrace();
		}
		return resultJSON;
	}
	
}
