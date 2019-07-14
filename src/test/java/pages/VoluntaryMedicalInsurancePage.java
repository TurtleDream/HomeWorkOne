package pages;

import library.BasePage;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class VoluntaryMedicalInsurancePage extends BasePage {

    @FindBy(className = "content-document-header")
    private WebElement title;

    @FindBy(xpath = "//a[contains(text(),'Отправить заявку')]")
    private WebElement sendRequest;

    @FindBy(name = "LastName")
    private WebElement lastName;

    @FindBy(name = "FirstName")
    private WebElement firstName;

    @FindBy(name = "MiddleName")
    private WebElement middleName;

    @FindBy(name = "Region")
    private WebElement region;

    @FindBy(name = "Email")
    private WebElement email;

    @FindBy(name = "Comment")
    private WebElement comment;

    @FindBy(id = "button-m")
    private WebElement sendButton;

    @FindBy(xpath = "//label[contains(text(),'Я согласен на')]")
    private WebElement consent;

    @FindBy(xpath = "//*[@id=\"applicationForm\"]/div[2]/div[5]/input")
    private WebElement phoneNumber;

    @FindBy(className = "validation-error-text")
    private WebElement errorText;

    @FindBy(className = "form-group col-md-12 col-xs-12")
    private WebElement regionLable;

    @FindBy(css = "[value='77']")
    private WebElement msk;

    public VoluntaryMedicalInsurancePage(WebDriver webDriver) {
        super(webDriver);
    }

    public VoluntaryMedicalInsurancePage checkTitle(){
        Assert.assertTrue(title.getText().contains("добровольное медицинское страхование"));
        return this;
    }

    public VoluntaryMedicalInsurancePage sendRequest(){
        Click(sendRequest);
        return this;
    }

    public VoluntaryMedicalInsurancePage checkPageForText(String text){
        Assert.assertTrue(CheckPageContainsText(text));
        return this;
    }

    public VoluntaryMedicalInsurancePage fillInTheForm(JSONObject jsonObject){
        SetText(lastName, jsonObject.get("lastName").toString());
        SetText(firstName, jsonObject.get("firstName").toString());
        SetText(middleName, jsonObject.get("middleName").toString());
        SetText(phoneNumber, jsonObject.get("phoneNumber").toString());
        SetText(email, jsonObject.get("email").toString());
        SetText(comment, jsonObject.get("comment").toString());
        Select select = new Select(region);
        select.selectByValue("77");

        return this;
    }

    public VoluntaryMedicalInsurancePage checkForm(JSONObject jsonObject){
        Assert.assertTrue("1",CheckEqualsText(lastName, jsonObject.get("lastName").toString()));
        Assert.assertTrue("2",CheckEqualsText(firstName, jsonObject.get("firstName").toString()));
        Assert.assertTrue("3",CheckEqualsText(middleName, jsonObject.get("middleName").toString()));
        Assert.assertTrue(CheckEqualsText(phoneNumber, "+7" + jsonObject.get("phoneNumber").toString()));
        Assert.assertTrue("4",CheckEqualsText(email, jsonObject.get("email").toString()));
        Assert.assertTrue("5",CheckEqualsText(comment, jsonObject.get("comment").toString()));
        Select select = new Select(region);

        Assert.assertTrue(select.getAllSelectedOptions().contains(msk));
        Click(consent);

        Actions actions = new Actions(this.webDriver);
        actions.moveToElement(sendRequest).click().build().perform();

        Assert.assertTrue(errorText.getText().contains("Введите адрес электронной почты"));

        email.clear();
        SetText(email, jsonObject.get("email").toString() + "@qwerty.ru");

        return this;
    }

}
