package com.hz.dxf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hz.dxf.dao.DataBaseParamMapper;
import com.hz.dxf.dao.factory.config.ConfigModel;
import com.hz.dxf.dao.factory.service.DataBaseConfigService;
import com.hz.dxf.dao.model.DataBaseParamModel;
import com.hz.dxf.service.IDataBaseService;
import com.hz.dxf.util.JsonUtils;
import com.hz.dxf.util.SystemExceiptionUtils;

/**
 * @Desscription : 数据源接入服务实现
 * @author Blossom
 * @time 2016年12月23日 下午5:42:34
 */
@Service("dataBaseService")
public class DataBaseServiceImp implements IDataBaseService {
	private static Logger logging = Logger.getLogger(DataBaseServiceImp.class);
	@Autowired
	private DataBaseParamMapper dataBaseParamMapper;

	/**
	 * @Description : 数据源设置
	 * @author Blossom
	 * @time 2016年12月23日 下午5:42:52
	 * @see com.hz.dxf.service.IDataBaseService#setUpDataConnection(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	public JSONObject setUpDataConnection(JSONObject pJson) {
		
		logging.info("DataBaseServiceImp》》》》》》setUpDataConnection： pJson=" + pJson.toString());
		try {
			if (!JsonUtils.checkJSONKey(pJson, "dpId")) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", pJson.getString("dpId"));
				List<DataBaseParamModel> dataBaseParamList = this.dataBaseParamMapper.queryDataBaseParam(map);
				if ((dataBaseParamList == null) || (dataBaseParamList.isEmpty())) {
					return JsonUtils.sealedJSON("status", "0", "message", "参数错误!");
				}
				
				ConfigModel.setDataBaseParamModel((DataBaseParamModel) dataBaseParamList.get(0));
				return JsonUtils.sealedJSON("status", "1", "message", "");
			}

			if (JsonUtils.checkJSONKey(pJson, "dbType") || JsonUtils.checkJSONKey(pJson, "dbName")
					|| JsonUtils.checkJSONKey(pJson, "address") || JsonUtils.checkJSONKey(pJson, "port")
					|| JsonUtils.checkJSONKey(pJson, "user") || JsonUtils.checkJSONKey(pJson, "password")) {
				logging.error("DataBaseServiceImp》》》》》》setUpDataConnection： 参数不全");
				return JsonUtils.sealedJSON("status", "0", "message", "参数不全!");
			}

			Map<String, String> map = new HashMap<String, String>();
			map.put("dbType", pJson.getString("dbType"));
			map.put("dbName", pJson.getString("dbName"));
			map.put("password", pJson.getString("password"));
			map.put("port", pJson.getString("port"));
			map.put("userName", pJson.getString("user"));
			map.put("address", pJson.getString("address"));

			List<DataBaseParamModel> dParamModels = dataBaseParamMapper.checkDataBaseParam(map);
			if ((null == dParamModels || dParamModels.isEmpty() || dParamModels.size() == 0) ? true : false) {// 此连接信息不存在
																												// 添加
				DataBaseParamModel dbParamModel = new DataBaseParamModel();
				dbParamModel.setAddress(pJson.getString("address"));
				dbParamModel.setDbName(pJson.getString("dbName"));
				dbParamModel.setDbType(pJson.getString("dbType"));
				dbParamModel.setPassword(pJson.getString("password"));
				dbParamModel.setPort(pJson.getString("port"));
				dbParamModel.setUserName(pJson.getString("user"));
				dbParamModel.setId(com.hz.dxf.util.Md5.MD5(java.lang.System.nanoTime() + "", 32)[1]);
				dbParamModel.setStatus(1);

				logging.info("DataBaseServiceImp》》》》》》setUpDataConnection：DataBaseParamModel1=" + dbParamModel.toString());
				dbParamModel = DataBaseConfigService.processDBType(dbParamModel);
				
				logging.info("DataBaseServiceImp》》》》》》setUpDataConnection：DataBaseParamModel2=" + dbParamModel.toString());
				int intTag = this.dataBaseParamMapper.saveDataBaseParam(dbParamModel);
				if (intTag == 0) {
					logging.error("DataBaseServiceImp》》》》》》setUpDataConnection： 参数设置失败!");
					return JsonUtils.sealedJSON("status", "0", "message", "设置失败!");
				}
				ConfigModel.setDataBaseParamModel(dbParamModel);
				return JsonUtils.sealedJSON("status", "1", "message", "");
			}
			// 此连接信息已经存在
			DataBaseParamModel dParamModel = dParamModels.get(0);
			if (dParamModel.getStatus() == 0) { // 此信息不可连接 修改
				dParamModel.setStatus(1);
				int intTag = dataBaseParamMapper.updateDataBaseParam(dParamModel);
				if (intTag == 0) {

					logging.error("DataBaseServiceImp》》》》》》setUpDataConnection： 参数设置失败!");
					return JsonUtils.sealedJSON("status", "0", "message", "添加失败!");
				}
				
				ConfigModel.setDataBaseParamModel(dParamModel);
				return JsonUtils.sealedJSON("status", "1", "message", "");
			}
			// 存在并且可用 提示用户此信息存在
			logging.error("DataBaseServiceImp》》》》》》setUpDataConnection： 此连接信息已经存在!");
			return JsonUtils.sealedJSON("status", "0", "message", "此信息已经存在!");

		} catch (SystemExceiptionUtils e) {
			logging.error("DataBaseServiceImp》》》》》》setUpDataConnection： error" + e.getMessage());
			e.printStackTrace();
			return JsonUtils.sealedExceptionJSON(e);
		}
	}

	/**
	 * @Description : 获取链接信息
	 * @author Blossom
	 * @time 2016年12月23日 下午5:43:04
	 * @see com.hz.dxf.service.IDataBaseService#queryDataBaseParam(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	public JSONObject queryDataBaseParam(JSONObject pJson) {
		logging.info("DataBaseServiceImp》》》》》》queryDataBaseParam： pJson=" + pJson.toString());
		try {
			String id = "";
			if (!JsonUtils.checkJSONKey(pJson, "id")) {
				id = pJson.getString("id");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);

			List<DataBaseParamModel> dataBaseParamList = this.dataBaseParamMapper.queryDataBaseParam(map);

			return JsonUtils.sealedJSON("status","1","message","","dataBaseParams", dataBaseParamList);
		} catch (SystemExceiptionUtils e) {
			logging.error("DataBaseServiceImp》》》》》》queryDataBaseParam： error" + e.getMessage());
			e.printStackTrace();
			return JsonUtils.sealedExceptionJSON(e);
		}
	}

	/**
	 * @Description : 更新连接信息
	 * @author Blossom
	 * @time 2016年12月23日 下午5:43:20
	 * @see com.hz.dxf.service.IDataBaseService#updateDataBaseParam(net.sf.json.JSONObject)
	 * @param pJson
	 * @return
	 */
	public JSONObject updateDataBaseParam(JSONObject pJson) {
		logging.info("DataBaseServiceImp》》》》》》updateDataBaseParam： pJson=" + pJson.toString());
		try {
			if ((!pJson.containsKey("id")) || (pJson.getString("id") == null) || "".equals(pJson.getString("id"))) {
				logging.error("DataBaseServiceImp》》》》》》updateDataBaseParam： 参数不全");
				return JsonUtils.sealedJSON("status", "0", "message", "参数不全!");
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", pJson.getString("id"));

			List<DataBaseParamModel> dataBaseParamList = this.dataBaseParamMapper.queryDataBaseParam(map);
			if ((dataBaseParamList == null) || (dataBaseParamList.isEmpty())) {
				logging.error("DataBaseServiceImp》》》》》》updateDataBaseParam： 此连接信息不存在!");
				return JsonUtils.sealedJSON("status", "0", "message", "此连接信息不存在!");
			}
			
			DataBaseParamModel dParamModel = (DataBaseParamModel) dataBaseParamList.get(0);
			if (!JsonUtils.checkJSONKey(pJson, "status")) { // 删除信息(逻辑删除)
				dParamModel.setStatus(pJson.getInt("status"));
				logging.info("DataBaseServiceImp》》》》》》updateDataBaseParam：DataBaseParamModel.status=" + dParamModel.getStatus());
			} else { // 修改信息
				if (pJson.containsKey("dbName")) {
					dParamModel.setDbName(pJson.getString("dbName"));
				}
				if (pJson.containsKey("address")) {
					dParamModel.setAddress(pJson.getString("address"));
				}
				if (pJson.containsKey("port")) {
					dParamModel.setPort(pJson.getString("port"));
				}
				if (pJson.containsKey("user")) {
					dParamModel.setUserName(pJson.getString("user"));
				}
				if (pJson.containsKey("password")) {
					dParamModel.setPassword(pJson.getString("password"));
				}
				logging.info("DataBaseServiceImp》》》》》》updateDataBaseParam：DataBaseParamModel1=" + dParamModel.toString());
				dParamModel = DataBaseConfigService.processDBType(dParamModel);
			}
			
			logging.info("DataBaseServiceImp》》》》》》updateDataBaseParam：DataBaseParamModel2=" + dParamModel.toString());
			int intTag = dataBaseParamMapper.updateDataBaseParam(dParamModel);
			if (intTag == 0) {
				logging.error("DataBaseServiceImp》》》》》》updateDataBaseParam： 修改失败!");
				return JsonUtils.sealedJSON("status", "0", "message", "修改失败!");
			}
			
			return JsonUtils.sealedJSON("status", "1", "message", "修改成功!");
		} catch (SystemExceiptionUtils e) {
			logging.error("DataBaseServiceImp》》》》》》updateDataBaseParam： error" + e.getMessage());
			e.printStackTrace();
			return JsonUtils.sealedExceptionJSON(e);
		}
	}
}
