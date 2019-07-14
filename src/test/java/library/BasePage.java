package library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWaitVisible;
    protected WebDriverWait webDriverWaitClickable;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWaitVisible = new WebDriverWait(webDriver, 15);
        webDriverWaitClickable = new WebDriverWait(webDriver, 15);
    }

    public void wait (WebElement webElement){
        webDriverWaitVisible.until(ExpectedConditions.elementToBeClickable(webElement));
        webDriverWaitClickable.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void Click(WebElement webElement){
        wait(webElement);
        webElement.click();
    }

    public boolean CheckPageContainsText(String text){
        try {
            webDriver.getPageSource().contains(text);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }


    public boolean CheckEqualsText(WebElement webElement,String text){
        try {
            webElement.getText().equals(text);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }

    public void SetText(WebElement webElement, String text){
        webElement.sendKeys(text);
    }
}
