import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.localization.ILocalizedLogger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import config.TestEnvironment;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Optional;

public class BaseTest {

    protected final ILocalizedLogger log = AqualityServices.getLocalizedLogger();
    Browser browser = AqualityServices.getBrowser();
    ISettingsFile environment = new JsonSettingsFile("environment/" +
            Optional.ofNullable(System.getProperty("environment")).orElse(TestEnvironment.TEST_1.getEnvFileName()));

    @BeforeTest
    protected synchronized void setUp() {
        log.info("Starting tests");
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(environment.getValue("/baseUrl").toString());
        browser.waitForPageToLoad();
    }

    @AfterTest
    protected synchronized void tearDown() {
        log.info("Ending tests");
        browser.quit();
    }
}
