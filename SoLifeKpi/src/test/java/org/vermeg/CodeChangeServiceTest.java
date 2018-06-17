package org.vermeg;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SoLifeKpiApplication.class)
public class CodeChangeServiceTest {

    @Autowired
    private ElasticsearchTemplate esTemplate;
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
