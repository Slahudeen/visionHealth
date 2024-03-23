package com.visionHealth.Web.tests;

import com.visionHealth.Web.webPages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void verifyOutOfStockFunctionality() {
        chairsWebPage webPage = new chairsWebPage((driver));
        List<Integer> outOfStockItems = webPage.findOutOfStockItems();
        System.out.println("Out of stock items in chair page are = " + outOfStockItems.toString());
        webPage.addOutOfStockItemsToCart(outOfStockItems);

    }

    @Test
    public void verifyAddToCartAndCheckoutFunctionality() throws InterruptedException {
        chairsWebPage chairsPage = new chairsWebPage(driver);
        List<Integer> inStockItems = chairsPage.findInStockItems();
        System.out.println("In stock items in chair page are = " + inStockItems.toString());
        if(inStockItems.size()>=3){
            String[][] description = new String[3][2];
            for(int i =0;i<3;i++){
                chairsPage = new chairsWebPage(driver);
                String productURL = chairsPage.viewTheProduct(inStockItems, inStockItems.get(i));
                productWebPage productPage = new productWebPage(driver, productURL);
                description[i]= productPage.addItemToCart();

            }
            cartWebPage cartPage = new cartWebPage(driver);
            cartPage.verifyTheItemsAddedToCart(description);
            cartPage.checkout();

        }

    }

    @Test
    public void navigateToProfilePage(){
        profileWebPage profilePage = new profileWebPage(driver);
        profilePage.navigateToProfilePage();

    }

    @Test
    public void navigateToEditProfilePage(){
        profileWebPage profilePage = new profileWebPage(driver);
        profilePage.navigateToEditProfilePage();

    }

    @Test
    public void editAndSaveAddress() throws InterruptedException {
        editProfileWebPage editProfilePage = new editProfileWebPage(driver);
        editProfilePage.editAndSaveAddress();
        editProfilePage.clickOnViewUpdatedProfileButton();
        profileWebPage profilePage = new profileWebPage(driver);
        profilePage.verifyTheNewAddressGotSaved(editProfilePage.address,editProfilePage.city, editProfilePage.postalCode, editProfilePage.state);

    }

    @AfterMethod
    public void close(){
        driver.close();

    }

}
