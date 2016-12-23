package com.hz.dxf.util.excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @author Blossom
 * @time 2016年10月22日 下午5:06:53
 */
public class UserModelTest {

	@Excel(name="姓名",width = 30)
	private String name;
	
	@Excel(name = "年龄",width = 60)
	private String age;
	
	@Excel(name = "xx")
	private Double xx;
	
	@Excel(name = "yy")
	private Date yy;
	
	@Excel(name = "锁定")
	private Boolean locked;
	
	@Excel(name = "金额")
	private BigDecimal db;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the xx
	 */
	public Double getXx() {
		return xx;
	}

	/**
	 * @param xx the xx to set
	 */
	public void setXx(Double xx) {
		this.xx = xx;
	}

	/**
	 * @return the yy
	 */
	public Date getYy() {
		return yy;
	}

	/**
	 * @param yy the yy to set
	 */
	public void setYy(Date yy) {
		this.yy = yy;
	}

	/**
	 * @return the locked
	 */
	public Boolean getLocked() {
		return locked;
	}

	/**
	 * @param locked the locked to set
	 */
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the db
	 */
	public BigDecimal getDb() {
		return db;
	}

	/**
	 * @param db the db to set
	 */
	public void setDb(BigDecimal db) {
		this.db = db;
	}
	
	
	
}
