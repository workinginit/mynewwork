package org.vermeg;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CodeChangeServiceTest.class, CodeQualityServiceTest.class })
public class IntegrationTest {

}
