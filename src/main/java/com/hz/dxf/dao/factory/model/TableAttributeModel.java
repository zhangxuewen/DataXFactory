package com.hz.dxf.dao.factory.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Description : 表详细属性
 * @author johb
 * @time 2016年11月24日 上午11:36:24
 */
public class TableAttributeModel implements Serializable {

	private static final long serialVersionUID = -2807311634682012661L;
	
	//表名
	private String tableName;
	//外键关联表
	private List<String> exportedKeysTables;
	//表属性
	private List<AttributeDetailsModel> attributeDetails;
	public List<String> getExportedKeysTables() {
		return exportedKeysTables;
	}
	public void setExportedKeysTables(List<String> exportedKeysTables) {
		this.exportedKeysTables = exportedKeysTables;
	}
	public List<AttributeDetailsModel> getAttributeDetails() {
		return attributeDetails;
	}
	public void setAttributeDetails(List<AttributeDetailsModel> attributeDetails) {
		this.attributeDetails = attributeDetails;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Override
	public String toString() {
		return "TableAttributeModel [tableName=" + tableName
				+ ", exportedKeysTables=" + exportedKeysTables
				+ ", attributeDetails=" + attributeDetails + "]";
	}

	
	
	

}
