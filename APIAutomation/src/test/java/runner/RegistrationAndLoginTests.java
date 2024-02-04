package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features={"src//test//java//features//RegistrationandLoginTests.feature"},glue={"stepdefinations"}
,plugin={"pretty","html:target/cucumber"})

public class RegistrationAndLoginTests extends AbstractTestNGCucumberTests{

}
