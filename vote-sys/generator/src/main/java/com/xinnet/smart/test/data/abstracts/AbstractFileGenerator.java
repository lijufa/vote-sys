package com.xinnet.smart.test.data.abstracts;

import java.io.File;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UFile;
import com.xinnet.smart.base.util.UString;
import com.xinnet.smart.test.data.impl.UTypeMysql;
import com.xinnet.smart.test.model.FieldDesc;

public abstract class AbstractFileGenerator extends AbstractTableHandler {
	static final Logger logger = LoggerFactory.getLogger(AbstractFileGenerator.class);
	protected String primaryKey;
	protected String primaryKeyArray;

	public void deletes() {
		File file = new File(getPath());
		File[] subFiles = file.listFiles();
		if (subFiles != null) {
			for (File subFile : subFiles) {
				subFile.delete();
			}
		}
	}

	protected abstract String generate(String tableName);

	protected abstract String getPath();

	protected abstract String getFileName(String tableName);

	@Override
	public void handle(String tableName) {
		if (UString.notEmpty(tableName)) {
			logger.debug(tableName);
			primaryKey = database.primaryKey(tableName);
			if (primaryKey == null) {
				primaryKey = "id";
			}
			primaryKeyArray = primaryKey + "__array";
			String s = generate(tableName);
			logger.debug(s);
			if (UString.notEmpty(s)) {
				StringBuilder sb = new StringBuilder();
				sb.append(getPath().replace('/', File.separatorChar));
				sb.append(getFileName(tableName));
				File file = UFile.prepare(sb.toString());
				UFile.write(file, s, Charsets.UTF_8, false);
			}
		}
	}

	protected FieldDesc where() {
		FieldDesc ret = new FieldDesc();
		ret.setName("__where");
		ret.setJavaType("String");
		ret.setComment("定制化查询条件");
		ret.setJsonIgnore(true);
		return ret;
	}

	protected FieldDesc primaryKeyArray(FieldDesc primaryKeyField) {
		if (primaryKeyField == null) {
			return null;
		}
		FieldDesc ret = new FieldDesc();
		ret.setName(primaryKeyArray);
		ret.setJavaType(UTypeMysql.parseClassName(primaryKeyField.getType()) + "[]");
		ret.setComment(primaryKeyField.getComment() + "（批量更新）");
		ret.setJsonIgnore(true);
		return ret;
	}
}
