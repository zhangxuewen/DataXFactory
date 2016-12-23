package com.hz.dxf.dao.factory.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.hz.dxf.dao.factory.config.ConfigModel;
import com.hz.dxf.dao.model.DataBaseParamModel;
import com.hz.dxf.util.StringUtils;

/**
 * 数据库操作
 * 
 * @author johb
 * @time 2016年10月13日
 */
public class DBUtils {

	private static Logger logger = Logger.getLogger(DBUtils.class);

	/**
	 * @Description : 获取数据库连接
	 * @author johb
	 * @time 2016年11月17日 下午2:04:19
	 *
	 */
	public static Connection getConnection(DataBaseParamModel dataBaseModel)
			throws ClassNotFoundException, SQLException {
		if (StringUtils.isEmpty(dataBaseModel.getDriver()) || StringUtils.isEmpty(dataBaseModel.getUrl())) {
			logger.error("数据库连接》》》》》》》参数不全!");
			throw new NullArgumentException("参数不全!");
		}
		// 加载数据库资源
		Class.forName(dataBaseModel.getDriver());
		// 获取数据库连接
		return DriverManager.getConnection(dataBaseModel.getUrl(), dataBaseModel.getUserName(),
				dataBaseModel.getPassword());
	}

	/**
	 * druid数据库连接池
	 * 
	 * @author johb
	 * @time 2016年10月14日
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static DruidPooledConnection getDruidConnection() throws SQLException, IOException {
		/*
		 * Properties properties = new Properties(); InputStream inputStream =
		 * DBUtils.class.getResourceAsStream("config.properties");
		 * properties.load(inputStream);
		 * 
		 * int minIdle = Integer.parseInt(properties.getProperty("minIdle"));
		 * int maxActive =
		 * Integer.parseInt(properties.getProperty("maxActive")); String filters
		 * = properties.getProperty("filters"); boolean poolPreparedStatements =
		 * Boolean.parseBoolean(properties.getProperty("poolPreparedStatements")
		 * );
		 */
		DruidDataSource dataSource = new DruidDataSource();

		dataSource.setDriverClassName(ConfigModel.getDataBaseParamModel().getDriver());
		dataSource.setUsername(ConfigModel.getDataBaseParamModel().getUserName());
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(10);
		dataSource.setFilters("stat");
		dataSource.setPoolPreparedStatements(false);
		dataSource.setPassword(ConfigModel.getDataBaseParamModel().getPassword());

		return dataSource.getConnection();

	}

	/**
	 * 释放资源
	 * 
	 * @author johb
	 * @time 2016年10月13日
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (null != rs) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				rs = null;
				logger.error(e.getMessage());
				e.printStackTrace();
			} finally {
				rs = null;
				logger.info("rs 资源释放成功!");
			}
		}
		if (null != stmt) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				stmt = null;
				logger.error(e.getMessage());
				e.printStackTrace();
			} finally {
				stmt = null;
				logger.info("stmt 资源释放成功!");
			}
		}
		if (null != conn) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				conn = null;
				logger.error(e.getMessage());
				e.printStackTrace();
			} finally {
				conn = null;
				logger.info("conn 资源释放成功!");
			}
		}
	}

}
