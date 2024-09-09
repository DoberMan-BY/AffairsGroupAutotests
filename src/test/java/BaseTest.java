import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import config.TestEnvironment;
import helpers.allure.LogsToAllure;
import helpers.allure.ScreenshotHelper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Optional;

public class BaseTest {

    protected Logger log;
    Browser browser = AqualityServices.getBrowser();
    ISettingsFile environment = new JsonSettingsFile("environment/" +
            Optional.ofNullable(System.getProperty("environment")).orElse(TestEnvironment.TEST_1.getEnvFileName()));

    @BeforeMethod(alwaysRun = true)
    protected synchronized void setUp() {
        log =  AqualityServices.getLogger();
        LogsToAllure.setupLogging();
        log.info("Starting tests");
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(environment.getValue("/baseUrl").toString());
        browser.waitForPageToLoad();
    }

    @AfterMethod(alwaysRun = true)
    protected synchronized void tearDown(ITestResult result) {
        log.info("Ending tests");
        LogsToAllure.attachLogsToAllure();
        if (!result.isSuccess()) {
            ScreenshotHelper.addScreenShotToAllure(browser.getDriver());
        }
        browser.quit();
    }
}
