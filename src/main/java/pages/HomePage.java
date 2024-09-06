package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.openqa.selenium.By.xpath;

public class HomePage extends Form {

    private static final String FORM_LOCATOR = "//div[@id='portfolio-grid1']";
    private static final Logger log = LoggerFactory.getLogger(HomePage.class);
    private IButton btnLogIn = AqualityServices.getElementFactory().getButton(By.xpath("//li//a[contains(@href,'login-page')]"), "Log In");

    public HomePage() {
        super(xpath(FORM_LOCATOR), "Home Page");
    }

    public void clickSignUp() {
        btnLogIn.click();
    }
}

