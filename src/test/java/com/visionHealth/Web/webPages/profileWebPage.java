package com.visionHealth.Web.webPages;

import com.visionHealth.Web.config.WebConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class profileWebPage {
    private WebDriver driver;
    WebConfig wc = new WebConfig();
    private String URL = wc.baseURL() + "/profile";

    @FindBy(className = "button")
    private WebElement editProfileButton;

    @FindBy(className = "profile")
    private WebElement profileButton;

    @FindBy(xpath = "//*[@id=\"main\"]/section/div/div[2]/div[2]")
    private WebElement addressDetails;

    public profileWebPage(WebDriver driver ){
        this.driver = driver;
        driver.get(URL);
        PageFactory.initElements(driver, this);

    }

    public void navigateToProfilePage(){
        driver.get(wc.baseURL());
        profileButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), wc.baseURL() + "/profile");

    }

    public void navigateToEditProfilePage(){
        editProfileButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), wc.baseURL() + "/profile-edit");

    }

    public void verifyTheNewAddressGotSaved(String address, String city, String postalCode, String state){
        String savedAddress = addressDetails.getText().split("\n")[0];
        String savedCity = addressDetails.getText().split("\n")[2].split(", ")[0];
        System.out.println(savedCity);
        String savedState = addressDetails.getText().split("\n")[2].split(", ")[1];
        System.out.println(savedState);
        String savePostalCode = addressDetails.getText().split("\n")[2].split(", ")[2];
        Assert.assertEquals(savedAddress, address);
        Assert.assertEquals(savedCity, city);
        Assert.assertEquals(savedState, state);
        Assert.assertEquals(savePostalCode, postalCode);

    }

}
