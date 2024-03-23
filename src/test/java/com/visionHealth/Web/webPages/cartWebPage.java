package com.visionHealth.Web.webPages;

import com.visionHealth.Web.config.WebConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class cartWebPage {

    private WebDriver driver;
    WebConfig wc = new WebConfig();
    private String URL = wc.baseURL() + "/cart";

    String checkoutSuccess = "Thank you!";
    String checkoutMessage1 = "Your order has been placed.";
    String checkoutMessage2 = "We will update you when it's shipped.";

    @FindAll(@FindBy(className = "product-description"))
    private List<WebElement> productDescription;

    @FindAll(@FindBy(className = "line"))
    private List<WebElement> amountDetails;

    @FindBy(className = "checkout-title")
    private WebElement checkoutTitle;

    @FindBy(className = "checkout")
    private WebElement checkout;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div[2]/div[1]")
    private WebElement checkoutMessageA;

    @FindBy(xpath = "//*[@id=\"main\"]/div/div[2]/div[2]")
    private WebElement checkoutMessageB;

    public cartWebPage(WebDriver driver ){
        this.driver = driver;
        driver.get(URL);
        PageFactory.initElements(driver, this);

    }

    //This function is to verify the items, descriptions and prices and total price) that i added in my other test to the cart are the ones that are showing in the cart
    public void verifyTheItemsAddedToCart(String[][] details) {
        double myTotal = 0.0;
        for(int i = 0; i< details.length;i++){
            Assert.assertEquals(productDescription.get(i).findElements(By.tagName("div")).get(0).getText().split(" \\(")[0],details[i][0]);
            Assert.assertEquals(productDescription.get(i).findElements(By.tagName("div")).get(0).getText().split(" \\(")[1].split("\\)")[0],details[i][1]);
            myTotal = Double.parseDouble(details[i][1].split("\\$")[1]) + myTotal;
        }
        Double total = Double.parseDouble(amountDetails.get(0).findElements(By.tagName("div")).get(1).getText().split("\\$")[1]);
        Assert.assertEquals(myTotal,total);

    }

    //This function is to verify that user checkout successfully
    public void checkout() throws InterruptedException {
        checkout.click();
        Thread.sleep(2000);
        assertTrue(checkoutTitle.getText().equalsIgnoreCase(checkoutSuccess));
        assertTrue(checkoutMessageA.getText().equalsIgnoreCase(checkoutMessage1));
        assertTrue(checkoutMessageB.getText().equalsIgnoreCase(checkoutMessage2));

    }
}
