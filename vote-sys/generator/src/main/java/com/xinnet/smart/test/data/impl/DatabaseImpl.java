package com.xinnet.smart.test.data.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UString;
import com.xinnet.smart.base.util.UTrace;
import com.xinnet.smart.test.data.IDatabase;
import com.xinnet.smart.test.data.IResultSetGetter;

/**
 * 数据库的基础实现
 * @author meitao
 * @date 2012-12-24
 */
public class DatabaseImpl implements IDatabase {
	static final Logger LOGGER = LoggerFactory.getLogger(DatabaseImpl.class);
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public <T> T query(String sql, IResultSetGetter<T> getter) {
		if (dataSource == null || sql == null) {
			return null;
		}
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			if (connection == null) {
				return null;
			}
			statement = connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			if (statement == null) {
				return null;
			}
			rs = statement.executeQuery(sql);
			return getter.get(rs);
		} catch (SQLNonTransientConnectionException e) {
			LOGGER.error(UTrace.causedBy(e));
			return null;
		} catch (Throwable e) {
			LOGGER.error(UTrace.trace(e, sql));
			return null;
		} finally {
			close(rs);
			close(statement);
			close(connection);
		}
	}

	@Override
	public int update(String sql) {
		if (dataSource == null) {
			return 0;
		}
		if (UString.isEmpty(sql)) {
			return 0;
		}
		Connection connection = null;
		Statement statement = null;
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement = connection.createStatement(ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CLOSE_CURSORS_AT_COMMIT);
			int ret = statement.executeUpdate(sql);
			connection.commit();
			return ret;
		} catch (SQLException e) {
			LOGGER.error(UTrace.trace(e, sql));
			return 0;
		} finally {
			close(statement);
			close(connection);
		}
	}

	void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}

	void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
	}

	void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			} finally {
				connection = null;
			}
		}
	}

	public static Logger getLogger() {
		return LOGGER;
	}
}
