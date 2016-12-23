package com.hz.dxf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: JSON工具类
 * @author Blossom
 * @time 2016年12月15日 下午8:57:53
 */
public class JsonUtils {
	
	private JsonUtils(){}
	
	/**
	 * @Description: 校验json对象是否为空
	 * @author Blossom
	 * @time 2016年12月17日 下午8:19:03
	 * @return_type boolean
	 *
	 */
	public static boolean checkJSONIsNull(JSONObject json){
		if (json == null || json.isEmpty() || json.isNullObject()) {
			return true;
		}
		return false;
	}

	/**
	 * @Description: 验证key是否存在，value是否为空
	 * @author Blossom
	 * @time 2016年12月15日 下午9:00:54
	 * @return_type boolean
	 *
	 */
	public static boolean checkJSONKey(JSONObject json,String key){
		if (!json.containsKey(key) || null == json.getString(key) || "".equals(json.getString(key))) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 封装json信息
	 * @author Blossom
	 * @throws SystemExceiptionUtils 
	 * @time 2016年12月15日 下午9:01:06
	 * @return_type JSONObject
	 *
	 */
	public static JSONObject sealedJSON(Object...objects) throws SystemExceiptionUtils{
		if ( objects == null) {
			throw new SystemExceiptionUtils(SystemExceiptionUtils.SYSTEM_INCOMPLETE_PARAMETER_ERROR);
		}
		JSONObject resultJSON = new JSONObject();
		for (int i = 0; i < objects.length; i = i+2) {
			resultJSON.put(objects[i], objects[i+1]);
		}
		return resultJSON;
	}
	
	/**
	 * @Description : 封装Exception信息为json格式
	 * @author Blossom
	 * @time 2016年12月21日 下午2:34:56
	 * @param exception
	 * @return
	 */
	public static JSONObject sealedExceptionJSON(Throwable exception){
		JSONObject resultJSON = new JSONObject();
		resultJSON.put("status", -1);
		resultJSON.put("message", exception.getMessage());
		return resultJSON;
	}
	
	/**
	 * @Description: 校验是否为JSONObject,JSONArray对象
	 * @author Blossom
	 * @time 2016年12月15日 下午9:01:18
	 * @return_type JSONObject
	 *
	 */
	public static JSONObject checkJSONObject(String param){
		JSONObject resultJSON = new JSONObject();
		try {
			JSONObject json = JSONObject.fromObject(param);
			resultJSON.put("data", json);
			return resultJSON;
		} catch (Exception e) {
			try {
				JSONArray array = JSONArray.fromObject(param);
				resultJSON.put("array", array);
				return resultJSON;
			} catch (Exception e2) {
				resultJSON.put("error", "格式错误!");
				return resultJSON;
			}
		}
	}
	
	/**
	 * @Description: 获取json当中的所有key
	 * @author Blossom
	 * @time 2016年12月15日 下午9:01:30
	 * @return_type List<String>
	 *
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public static List<String> getJSONKey(String param) throws Exception{
		
		Iterator<String> iterator = getIterator(param);
		JSONObject json = JSONObject.fromObject(param);
		List<String> keyList = new ArrayList<String>();
		String keyStr = null;
		while (iterator.hasNext()) {
			keyStr = iterator.next();
			keyList.add(keyStr);
		}
		return keyList;
	}
	
	/**
	 * @Description: 将json转换为map
	 * @author Blossom
	 * @time 2016年12月15日 下午9:01:41
	 * @return_type Map<String,Object>
	 *
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String param) throws Exception{
		
		Iterator<String> iterator = getIterator(param);
		JSONObject json = JSONObject.fromObject(param);
		Map<String, Object> map = new HashMap<String, Object>();
		String keyStr = null;
		Object object = null;
		while (iterator.hasNext()) {
			keyStr = iterator.next();
			object = json.get(keyStr);
			map.put(keyStr, object);
		}
		return map;
	}
	
	/**
	 * @Description: 获取json迭代
	 * @author Blossom
	 * @time 2016年12月15日 下午9:01:56
	 * @return_type Iterator
	 *
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	private static Iterator getIterator(String param) throws Exception{
		JSONObject json = checkJSONObject(param);
		if (!json.containsKey("data")) {
			throw new Exception("格式错误!");
		}
		json = JSONObject.fromObject(param);
		Iterator<String> iterator = json.keys();
		
		return iterator;
	}
	
}
