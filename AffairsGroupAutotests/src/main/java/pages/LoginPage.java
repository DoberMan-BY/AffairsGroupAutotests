package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginPage extends Form {

    private static final String FORM_LOCATOR = "//div[@id='ctl00_MainContent_pnlLogin']";
    private ITextBox txbUserName = AqualityServices.getElementFactory().getTextBox(By.xpath("//*[@id='ctl00_MainContent_tbUserName']"), "Username or E-mail Address");
    private ITextBox txbPassword = AqualityServices.getElementFactory().getTextBox(By.xpath("//*[@id='ctl00_MainContent_tbPassword']"), "Password");
    private IButton btnLogIn = AqualityServices.getElementFactory().getButton(By.xpath("//input[@type='submit' and @name='ctl00$MainContent$btnLogin']"), "Login");

    public LoginPage() {
        super(By.xpath(FORM_LOCATOR), "Login Page");
    }

    public void enterUserName(String userName) {
        txbUserName.sendKeys(userName);
    }

    public void enterPassword(String password) {
        txbPassword.sendKeys(password);
    }

    public void clickLogin() {
        btnLogIn.click();
    }
}
