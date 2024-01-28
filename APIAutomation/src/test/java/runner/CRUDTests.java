package runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features={"src//test//java//features//CRUDTests.feature"},glue={"stepdefinations"}
,plugin={"pretty","html:target/cucumber"})

@Test
public class CRUDTests extends AbstractTestNGCucumberTests{

}
