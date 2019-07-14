package pages;

import library.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RosgosstrakhPage extends BasePage {

    @FindBy(css = "[data-toggle='dropdown']")
    private WebElement insurance;

    @FindBy(linkText = "ДМС")
    private WebElement voluntaryMedicalInsurance;

    public RosgosstrakhPage(WebDriver webDriver) {
        super(webDriver);
    }

    public RosgosstrakhPage goToPage(){
        webDriver.get("https://www.rgs.ru/");
        return this;
    }

    public void goToVMI(){
        Click(insurance);
        Click(voluntaryMedicalInsurance);
    }
}
