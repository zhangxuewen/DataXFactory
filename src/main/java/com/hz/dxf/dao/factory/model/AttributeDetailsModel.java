package com.hz.dxf.dao.factory.model;


/**
 * 属性详情
 * @author johb
 * @time 2016年10月13日
 */
public class AttributeDetailsModel {

	//属性名称
	private String columnName;
	//属性类型
	private String columnType;
	//熟悉长度
	private int datasize;
	//小数位数
	private int digits;
	//是否为空
	private int nullable;
	//属性说明
	private String description;
	//是否为主键
	private boolean primaryKey;
	//是否为外键
	private boolean exportedKey;
	
	
	
	public boolean isExportedKey() {
		return exportedKey;
	}
	public void setExportedKey(boolean exportedKey) {
		this.exportedKey = exportedKey;
	}
	/**
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}
	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public int getDatasize() {
		return datasize;
	}
	public void setDatasize(int datasize) {
		this.datasize = datasize;
	}
	public int getDigits() {
		return digits;
	}
	public void setDigits(int digits) {
		this.digits = digits;
	}
	public int getNullable() {
		return nullable;
	}
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
