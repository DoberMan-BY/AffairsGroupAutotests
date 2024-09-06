import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.HomePage;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void firstTest() {
        log.info("Step 1: Navigate to Home Page");
        HomePage homePage = new HomePage();
        assertThat("'Home Page' page is not displayed", homePage.state().waitForDisplayed());

        log.info("Step 2: Entering credentials");
        homePage.clickSignUp();
        LoginPage loginPage = new LoginPage();
        assertThat("'Login Page' page is not displayed", loginPage.state().waitForDisplayed());
        loginPage.enterUserName(environment.getValue("/userName").toString());
        loginPage.enterPassword(environment.getValue("/userPassword").toString());
        loginPage.clickLogin();

        log.info("Step 3: Entering Dashboard");
        DashBoardPage dashBoardPage = new DashBoardPage();
        assertThat("'Dashboard Page' page is not displayed", dashBoardPage.state().waitForDisplayed());
    }
}
