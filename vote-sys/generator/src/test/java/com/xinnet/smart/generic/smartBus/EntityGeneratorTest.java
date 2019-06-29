package com.xinnet.smart.generic.smartBus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.xinnet.smart.test.data.generator.EntityGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-smartBus.xml" })
public class EntityGeneratorTest extends EntityGenerator {
	static final Logger logger = LoggerFactory.getLogger(EntityGeneratorTest.class);
	static final String PACKAGE_NAME = "com.xinnet.entity";

	@Override
	protected String getPath() {
		return "" + super.getPath();
	}

	@Override
	public String getClassName(String tableName) {
		return super.getClassName(tableName) + "Bean";
	}

	@Override
	public String getPackageName() {
		return PACKAGE_NAME;
	}

	//@Test
	public void test() throws Throwable {
		super.test();
	}

	@Test
	public void testGeneratorOneTable() throws Throwable {
		String[] tables = Constants.TABLE_NAME.split(",");
		for (String t : tables) {
			generatorOneTable(t);
		}
	}
}
