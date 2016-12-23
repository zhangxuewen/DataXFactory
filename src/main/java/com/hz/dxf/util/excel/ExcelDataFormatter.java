package com.hz.dxf.util.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Excel数据导入导出格式化
 * @author Blossom
 * @time 2016年10月22日 下午5:19:59
 */
public class ExcelDataFormatter {
	
	private Map<String,Map<String,String>> formatter=new HashMap<String, Map<String,String>>();
	 
    public void set(String key,Map<String,String> map){
        formatter.put(key, map);
    }
     
    public Map<String,String> get(String key){
        return formatter.get(key);
    }
	
}
