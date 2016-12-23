package com.hz.dxf.dao.model;

import java.io.Serializable;

/**
 * @Description : 数据库参数
 * @author johb
 * @time 2016年11月17日 上午10:53:04
 */
public class DataBaseParamModel implements Serializable {

	private static final long serialVersionUID = 8891746171479286694L;

	// 编号
	private String id;
	// 数据库类型
	private String dbType;
	// 数据库名称
	private String dbName;
	// 数据库地址
	private String address;
	// 数据库端口号
	private String port;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 标识
	private int status;
	// 驱动
	private String driver;
	// 链接地址
	private String url;

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver
	 *            the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @Description :
	 * @author Blossom
	 * @time 2016年11月17日 下午8:46:04
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "DataBaseParamModel [id=" + id + ", dbType=" + dbType + ", dbName=" + dbName + ", address=" + address
				+ ", port=" + port + ", userName=" + userName + ", password=" + password + ", status=" + status
				+ ", driver=" + driver + ", url=" + url + "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
