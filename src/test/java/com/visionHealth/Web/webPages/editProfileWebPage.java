package com.visionHealth.Web.webPages;

import com.visionHealth.Web.config.WebConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertTrue;

public class editProfileWebPage {
    private WebDriver driver;
    WebConfig wc = new WebConfig();
    private String URL = wc.baseURL() + "/profile-edit";

    public String address = "B7, 6";
    public String city = "Mannheim";
    public String state = "CA";
    public String postalCode = "68159";
    public String successMessage = "Profile successfully saved. View updated profile";


    @FindBy(id = "address1")
    private WebElement addressInput1;

    @FindBy(id = "address2")
    private WebElement addressInput2;

    @FindBy(id = "addressCity")
    private WebElement addressCityInput;

    @FindBy(id = "addressZipcode")
    private WebElement addressZipcodeInput;

    @FindBy(id = "addressState")
    private WebElement addressState;

    @FindBy(className = "vs__search")
    private WebElement stateSearchInput;

    @FindBy(xpath   = "//*[@class='button big inverted']")
    private WebElement saveButton;

    @FindBy(xpath   = "//*[@class='success banner']")
    private WebElement successBanner;

    public editProfileWebPage(WebDriver driver ){
        this.driver = driver;
        driver.get(URL);
        PageFactory.initElements(driver, this);

    }
    public void editAndSaveAddress(){
        addressInput1.clear();
        addressInput1.sendKeys(address);
        addressInput2.clear();
        addressInput2.sendKeys(address);
        addressCityInput.clear();
        addressCityInput.sendKeys(city);
        addressZipcodeInput.clear();
        addressZipcodeInput.sendKeys(postalCode);
        addressState.click();
        stateSearchInput.sendKeys(state + Keys.RETURN);
        saveButton.click();
        assertTrue(successBanner.getText().trim().equalsIgnoreCase(successMessage));

    }

    public void clickOnViewUpdatedProfileButton() throws InterruptedException {
        successBanner.findElement(By.tagName("a")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), wc.baseURL() + "/profile");

    }
}
