package com.gavin.job.dynamic.sync.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gavin.job.dynamic.sync.common.utils.log.LogUtils;


/**
 * jdbc工具类
 * 
 * @author gavin
 * 
 */
public class JdbcUtils {

	private JdbcUtils() {
	}

	public static Connection getConnection(String driveName, String url,
			String user, String password) throws SQLException,
			ClassNotFoundException {
		Class.forName(driveName);
		return DriverManager.getConnection(url, user, password);
	}

	public static void free(ResultSet resultset, Statement st, Connection conn) {
		try {
			if (resultset != null)
				resultset.close();
		} catch (SQLException e) {
			LogUtils.getInstance()
					.errorSystem(LogUtils.MODULE_COMMON, e);
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				LogUtils.getInstance().errorSystem(
						LogUtils.MODULE_COMMON, e);
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						LogUtils.getInstance().errorSystem(
								LogUtils.MODULE_COMMON, e);
					}
			}
		}
	}

	public static Statement createStatement(String driveName, String url,
			String user, String password, int maxRows) throws SQLException,
			ClassNotFoundException {

		// 1.建立连接
		Connection conn = getConnection(driveName, url, user, password);
		// 2.创建语句
		Statement st = conn.createStatement();
		// 3.执行语句
		st.setMaxRows(maxRows);

		return st;
	}
	
	
	public static Statement createStatement(String driveName, String url,
			String user, String password) throws SQLException,
			ClassNotFoundException {

		// 1.建立连接
		Connection conn = getConnection(driveName, url, user, password);
		// 2.创建语句
		Statement st = conn.createStatement();
		// 3.执行语句

		return st;
	}
	
	public static void create(String driveName, String url, String user,
			String password, String sql) throws SQLException,
			ClassNotFoundException {
		Connection conn = null;
		Statement st = null;
		ResultSet resultset = null;

		try {
			// 1.建立连接
			conn = getConnection(driveName, url, user, password);
			// 2.创建语句
			st = conn.createStatement();
			// 3.执行语句
			st.executeUpdate(sql);
		} finally {
			free(resultset, st, conn);
		}
	}
}
