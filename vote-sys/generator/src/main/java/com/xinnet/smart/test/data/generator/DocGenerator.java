package com.xinnet.smart.test.data.generator;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.util.UFile;
import com.xinnet.smart.base.util.UString;
import com.xinnet.smart.test.Handler;
import com.xinnet.smart.test.data.abstracts.AbstractTableHandler;
import com.xinnet.smart.test.model.FieldDesc;

/**
 * Doc生成工具
 * @author meitao
 * @date 2014-8-15 上午9:28:16
 */
public class DocGenerator extends AbstractTableHandler {
	static final Logger LOGGER = LoggerFactory.getLogger(DocGenerator.class);
	File file = UFile.prepare(getPath());

	protected String getPath() {
		return "db.doc";
	}

	@Override
	public void handle(String tableName) {
		String s = generate(tableName);
		if (UString.notEmpty(s)) {
			UFile.write(file, s, Charsets.UTF_8, true);
		}
	}

	@Override
	public void befores() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>\n");
		sb.append("<title></title>\n");
		sb.append("<meta content=\"text/html; charset=utf-8\" http-equiv=content-type />\n");
		//sb.append("<style>table{background:black;}td{background:white;}</style>\n");
		sb.append("<body>\n");
		UFile.write(file, sb.toString(), Charsets.UTF_8, false);
	}

	@Override
	public void afters() {
		StringBuilder sb = new StringBuilder();
		sb.append("</body>\n");
		sb.append("</html>\n");
		UFile.write(file, sb.toString(), Charsets.UTF_8, true);
	}

	String generate(String tableName) {
		Map<String, FieldDesc> fieldS = database.showFullColumns(tableName);
		StringBuilder sb = new StringBuilder();
		sb.append("<p>\n");
		sb.append(tableName);
		sb.append("(");
		sb.append(database.getComment(tableName));
		sb.append(")");
		sb.append("</p>\n");
		//sb.append("<table border=1 cellspacing=1 cellpadding=0>\n");
		sb.append("<table border=1 cellspacing=0 cellpadding=0>\n");
		sb.append("<tr>\n");
		sb.append("<td>字段</td>\n");
		sb.append("<td>类型（约束）</td>\n");
		sb.append("<td>默认值</td>\n");
		sb.append("<td>是否可空</td>\n");
		sb.append("<td>注释</td>\n");
		sb.append("</tr>\n");
		sb.append(generateFields(fieldS.values(), new Handler<FieldDesc, String>() {
			@Override
			public String handle(FieldDesc field) {
				StringBuilder sb = new StringBuilder();
				sb.append("<tr>\n");
				sb.append("<td>\n");
				sb.append(field.getName());
				sb.append("</td>\n");
				sb.append("<td>\n");
				sb.append(field.getType());
				sb.append("</td>\n");
				sb.append("<td>\n");
				sb.append(field.getDefaultValue());
				sb.append("</td>\n");
				sb.append("<td>\n");
				sb.append(field.isNull());
				sb.append("</td>\n");
				sb.append("<td>\n");
				sb.append(field.getComment());
				sb.append("</td>\n");
				sb.append("</tr>\n");
				return sb.toString();
			}
		}, ' '));
		sb.append("</table>\n");
		return sb.toString();
	}

	private String generateFields(Collection<FieldDesc> fieldS, Handler<FieldDesc, String> handler, char seperator) {
		StringBuilder sb = new StringBuilder();
		Iterator<FieldDesc> iterator = fieldS.iterator();
		if (iterator.hasNext()) {
			sb.append(handler.handle(iterator.next()));
		}
		while (iterator.hasNext()) {
			sb.append(seperator);
			sb.append(handler.handle(iterator.next()));
		}
		return sb.toString();
	}
}
