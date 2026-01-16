package com.greencity.cucumber;

import io.cucumber.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = { "src/test/resources/features/cancel_news.feature",
                "src/test/resources/features/crop_image.feature"},
        glue = {"com.greencity.cucumber.steps"}
)
public class TestRunnerCucumberMaryna extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass
    public void setUpClass() {

        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(description = "Example of BDD suite", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper feature) {
        testNGCucumberRunner.runScenario(pickle.getPickle());
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @Override
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

}