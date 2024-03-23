package com.visionHealth.Web.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class productWebPage {
    private WebDriver driver;

    @FindBy(className = "purchase-button")
    private WebElement addToCartButton;

    @FindBy(className = "cart")
    private WebElement cartIcon;

    @FindBy(className = "description")
    private WebElement productDescription;


    public productWebPage(WebDriver driver, String URL){
        this.driver = driver;
        driver.get(URL);
        PageFactory.initElements(driver, this);

    }

    public String[] addItemToCart() {
        String[] description = new String[2];
        int beforeCount = getCartCounter();
        description[0] = productDescription.findElement(By.tagName("div")).findElements(By.tagName("div")).get(0).getText();
        description[1] = productDescription.findElement(By.tagName("div")).findElements(By.tagName("div")).get(1).getText();
//        System.out.println("descriptions are = " + description[0] + "\n" + description[1]);
        addToCartButton.click();
        int afterCount = getCartCounter();
        Assert.assertEquals(beforeCount + 1, afterCount);
//        System.out.println("Before count was = " + beforeCount);
//        System.out.println("After count was = " + afterCount);
        return description;

    }
    public int getCartCounter(){
        String cartCounter = cartIcon.findElements(By.tagName("div")).get(1).getText();
        cartCounter = cartCounter.split("\\(")[1].split("\\)")[0];
        return Integer.parseInt(cartCounter);

    }

}
