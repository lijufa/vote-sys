package com.xinnet.smart.test.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetGetter<R> {
	R get(ResultSet rs) throws SQLException;
}
