package com.xinnet.smart.generic.smart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.xinnet.smart.test.data.generator.DaoGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-smart.xml" })
public class DaoGeneratorTest extends DaoGenerator {
	public DaoGeneratorTest() {
		super(new EntityGeneratorTest());
	}

	@Override
	protected String getPath() {
		return "../../war/controller/" + super.getPath();
	}

	@Override
	public String getPackageName(String url) {
		return "com.xinnet.smart.common.dao";
	}

	//	@Override
	//	@Test
	//	public void test() throws Throwable {
	//		super.test();
	//	}
	@Test
	public void testGeneratorOneTable() throws Throwable {
		generatorOneTable(Constants.TABLE_NAME);
	}
}
