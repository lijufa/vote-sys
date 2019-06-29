package com.xinnet.smart.generic.smart;

import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.xinnet.smart.test.data.generator.EntityGenerator;
import com.xinnet.smart.test.data.impl.UTypeMysql;
import com.xinnet.smart.test.model.FieldDesc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-smart.xml" })
public class EntityGeneratorTest extends EntityGenerator {
	static final String PACKAGE_NAME = "com.xinnet.smart.entity";

	@Override
	protected String getPath() {
		return "../../lib/vo/" + super.getPath();
	}

	@Override
	public String getClassName(String tableName) {
		return "T" + super.getClassName(tableName);
	}

	@Override
	public String getPackageName() {
		return PACKAGE_NAME;
	}

	@Override
	protected String defineClass(String tableName, Map<String, FieldDesc> fieldS) {
		StringBuilder sb = new StringBuilder();
		FieldDesc field = fieldS.get(primaryKey);
		if (field != null) {
			if (Long.class.equals(UTypeMysql.parse(field.getType()))) {
				sb.append(" implements ");
				sb.append("com.xinnet.smart.IUnique");
			}
		}
		return sb.toString();
	}

	//@Override
	//	@Test
	//	public void test() throws Throwable {
	//		super.test();
	//	}
	@Test
	public void testgeneratorOneTable() throws Throwable {
		generatorOneTable(Constants.TABLE_NAME);
	}
}
