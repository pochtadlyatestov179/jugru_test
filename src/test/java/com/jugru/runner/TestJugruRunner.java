package com.jugru.runner;

import com.jugru.listener.CustomListener;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import static com.jugru.steps.DriverSteps.getDriver;


@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "com.jugru.steps"
)
@Listeners(CustomListener.class)
public class TestJugruRunner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;
    //TODO
    /**
     *  Перед началом тестов в before должны быть подготовленны пользователи в системе (бд) с определенным набором билетов и профилем
     *  После выполнения тестов система должна быть очищена от созданных в before пользователей и преквизитов
     */


    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "features")
    public void TestScenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        if(getDriver()!=null)
            getDriver().quit();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}
