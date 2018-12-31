package com.ws.frame.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

public class DbUtils {
	private Connection conn = null;
	private static DbUtils dbUtils = null;

	public DbUtils() {
		super();
	}

	/**
	 * 初始化数据库操作类
	 * 
	 * @return this
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static DbUtils getInstance() {
		if (null == dbUtils) {
			dbUtils = new DbUtils();
		}
		return dbUtils;
	}

	/**
	 * 查询数据库并返回一个列表类型的数据
	 * 
	 * @param sql
	 *            查询sql语句
	 * @param params
	 *            查询参数
	 * @return list 返回值
	 * @throws SQLException
	 */
	public List<Map<String, Object>> query(String sql, Object[] params) {
		PreparedStatement pt = null;
		ResultSet rs = null;
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		try {
			pt = getPreparedStatement(sql, params);
			rs = pt.executeQuery();
			Map<String, Object> data = null;
			ResultSetMetaData dataMeta = rs.getMetaData();
			int count = dataMeta.getColumnCount();
			while (rs.next()) {
				data = new HashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					String columnLabel = dataMeta.getColumnLabel(i + 1);
					data.put(columnLabel, rs.getObject(columnLabel));
				}
				datas.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pt, rs);
		}
		return datas;
	}

	/**
	 * 设置 PreparedStatement
	 * 
	 * @param sql
	 * @param params
	 * @return pt
	 * @throws SQLException
	 */
	private PreparedStatement getPreparedStatement(String sql, Object[] params)
			throws Exception {
		javax.naming.Context ctx = new javax.naming.InitialContext();
		javax.sql.DataSource ds = (javax.sql.DataSource) ctx
				.lookup("java:/comp/env/jdbc/yq");
		conn = ds.getConnection();
		PreparedStatement pt = conn.prepareStatement(sql);
		if (null != params) {
			for (int i = 0; i < params.length; i++) {
				pt.setObject(i + 1, params[i]);
			}
		}
		return pt;
	}

	/**
	 * 纯sql操作数据库
	 * 
	 * @param sql
	 * @param params
	 * @return 操作了多少条
	 */
	public int execute(String sql, Object[] params) {
		PreparedStatement pt = null;
		int rows = 0;
		try {
			pt = getPreparedStatement(sql, params);
			rows = pt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pt, null);
		}
		return rows;
	}

	/**
	 * 关闭相应的数据库连接
	 * 
	 * @param conn
	 * @param pt
	 * @param rs
	 */
	private void close(Connection conn, PreparedStatement pt, ResultSet rs) {
		try {
			if (null != rs) {
				rs.close();
			}
			if (null != pt) {
				pt.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行批量请求
	 * 
	 * @param sql
	 * @param params
	 * @return >0 标识执行成功， -1标识执行失败
	 */
	public int executeBatch(String sql, List<Object[]> params) {
		PreparedStatement pt = null;
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx
					.lookup("java:/comp/env/jdbc/yq");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pt = conn.prepareStatement(sql);
			if (null != params) {
				for (Object[] objects : params) {
					for (int i = 0; i < objects.length; i++) {
						pt.setObject(i+1, objects[i]);
					}
					pt.addBatch();
				}
			}
			return pt.executeBatch().length;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(conn, pt, null);
		}
		return -1;
	}

	/**
	 * 查询一个long类型的数据，通常用来查询总记录数
	 * 
	 * @param sql
	 *            查询数量的sql
	 * @param params
	 *            where条件参数
	 * @return 数量
	 */
	public long getTotal(String sql, Object[] params) {
		PreparedStatement pt = null;
		ResultSet rs = null;
		long total = 0;
		try {
			pt = getPreparedStatement(sql, params);
			rs = pt.executeQuery();
			if (rs.next()) {
				total = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pt, rs);
		}
		return total;
	}

	/**
	 * 单条数据的查询方法，返回值是map类型的一条数据
	 * 
	 * @param sql
	 *            查询的sql
	 * @param params
	 *            查询的参数
	 * @return map类型的返回值
	 */
	public Map<String, Object> find(String sql, Object[] params) {
		PreparedStatement pt = null;
		ResultSet rs = null;
		Map<String, Object> data = null;
		try {
			pt = getPreparedStatement(sql, params);
			rs = pt.executeQuery();
			ResultSetMetaData dataMeta = rs.getMetaData();
			int count = dataMeta.getColumnCount();
			if (rs.next()) {
				data = new HashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					String columnLabel = dataMeta.getColumnLabel(i + 1);
					data.put(columnLabel, rs.getObject(columnLabel));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pt, rs);
		}
		return data;
	}
}
