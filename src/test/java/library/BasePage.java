package library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver webDriver;
    private WebDriverWait webDriverWaitVisible;
    private WebDriverWait webDriverWaitClickable;

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWaitVisible = new WebDriverWait(webDriver, 15);
        webDriverWaitClickable = new WebDriverWait(webDriver, 15);
    }

    private void wait (WebElement webElement){
        webDriverWaitVisible.until(ExpectedConditions.visibilityOf(webElement));
        webDriverWaitClickable.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void Click(WebElement webElement){
        wait(webElement);
        webElement.click();
    }

    protected boolean CheckPageContainsText(String text){
        if(webDriver.getPageSource().contains(text)){
            return true;
        }
        else return false;
    }


    protected boolean CheckEqualsText(WebElement webElement, String text){
        if(webElement.getText().equals(text)){
            return true;
        }
        else return false;
    }

    protected synchronized void SetText(WebElement webElement, String text){
        wait(webElement);
        webElement.click();
        webElement.sendKeys(text);
    }
}
