package org.vermeg.unittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CodeQualityServiceUnitTest.class, SettingsServiceUnitTest.class })
public class AllTests {

}
