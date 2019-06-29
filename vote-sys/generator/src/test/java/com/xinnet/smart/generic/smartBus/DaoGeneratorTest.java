package com.xinnet.smart.generic.smartBus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.xinnet.smart.test.data.generator.DaoGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-smartBus.xml" })
public class DaoGeneratorTest extends DaoGenerator {
	public DaoGeneratorTest() {
		super(new EntityGeneratorTest());
	}

	protected String getPath() {
		return "" + super.getPath();
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
