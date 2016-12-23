package com.hz.dxf.model;

import java.io.Serializable;

/**
 * @Description : SQL实体类
 * @author johb
 * @time 2016年12月2日 下午2:54:29
 */
public class SqlFormatModel implements Serializable {

	private static final long serialVersionUID = -6848424447010018285L;
	
	private String id;
	private String sqlFormat;
	private String createTime;
	private int status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSqlFormat() {
		return sqlFormat;
	}
	public void setSqlFormat(String sqlFormat) {
		this.sqlFormat = sqlFormat;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "SqlFormatModel [id=" + id + ", sqlFormat=" + sqlFormat
				+ ", createTime=" + createTime + ", status=" + status + "]";
	}
	
	

}
