package com.hz.dxf.dao.factory.service;

import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;

import com.hz.dxf.dao.model.DataBaseParamModel;
import com.hz.dxf.util.StringUtils;

/**
 * @Description : 数据库参数设置服务
 * @author johb
 * @time 2016年11月17日 上午11:48:57
 */
public class DataBaseConfigService {

	private static Logger logger = Logger.getLogger(DataBaseConfigService.class);

	/**
	 * @Description : 连接接数据库服务分发器
	 * @author johb
	 * @time 2016年11月17日 上午11:50:52
	 *
	 */
	public static DataBaseParamModel processDBType(DataBaseParamModel model) {
		logger.info("数据库服务分发器。。。。。。。。");
		if (StringUtils.isEmpty(model.getDbType()) || StringUtils.isEmpty(model.getDbName())
				|| StringUtils.isEmpty(model.getAddress()) || StringUtils.isEmpty(model.getPort())) {
			logger.error("数据库服务分发器》》》》》》参数不全!");
			throw new NullArgumentException("参数不全!");
		}

		// mySql数据库
		if ("MySql".equals(model.getDbType())) {
			String url = "jdbc:mysql://" + model.getAddress() + ":" + model.getPort() + "/" + model.getDbName();
			model.setUrl(url);
			model.setDriver("com.mysql.jdbc.Driver");
		}
		// SQL server数据库
		if ("SQLServer".equals(model.getDbType())) {
			String url = "jdbc:sqlserver://" + model.getAddress() + ":" + model.getPort() + "/" + model.getDbName();
			model.setUrl(url);
			model.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		// oracle数据库
		if ("oracle".equals(model.getDbType())) {
			String url = "jdbc:oracle:thin:@" + model.getAddress() + ":" + model.getPort() + ":" + model.getDbName();
			model.setUrl(url);
			model.setDriver("oracle.jdbc.driver.OracleDriver");
		}
		return model;
	}

}
