package com.xinnet.smart.generic.smartBus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.xinnet.smart.test.data.generator.DocGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-smartBus.xml" })
public class DocGeneratorTest extends DocGenerator {
	@Override
	@Test
	public void test() throws Throwable {
		super.test();
	}

	@Override
	protected String getPath() {
		return "../../db/mysql/mysql-smartBus.doc";
	}
}
