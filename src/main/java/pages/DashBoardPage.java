package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class DashBoardPage extends Form {

    private static final String FORM_LOCATOR = "//div[@id='myEvents']";
    private IButton btnLogIn = AqualityServices.getElementFactory().getButton(By.xpath("//li//a[contains(@href,'login-page')]"), "DashBoard");

    public DashBoardPage() {
        super(xpath(FORM_LOCATOR), "DashBoard Page");
    }
}

