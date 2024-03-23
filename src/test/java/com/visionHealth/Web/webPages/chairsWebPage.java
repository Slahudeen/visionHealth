package com.visionHealth.Web.webPages;

import com.visionHealth.Web.config.WebConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class chairsWebPage {

    private WebDriver driver;
    WebConfig wc = new WebConfig();
    private String URL = wc.baseURL() + "/department/chairs";
    public String outOfStockModalTitle = "Oops! This item is sold out.";
    public String outOfStockModalText = "It can't be added to your cart. We'll let you know when this item is back in stock.";
    public String outOfStockModalButtonText = "continue shopping";
    public String inStock = "in stock";
    public String outOfStock = "sold out";



    @FindAll(@FindBy(className = "product-card-container"))
    private List<WebElement> items;

    @FindAll(@FindBy(className = "status"))
    private List<WebElement> status;

    @FindAll(@FindBy(className = "modal-sold-out--is-open"))
    private List<WebElement> soldOutModal;

    @FindBy(className = "modal-title")
    private WebElement modalTitle;

    @FindBy(className = "modal-sold-out-content")
    private WebElement modalContent;

    @FindBy(className = "modal-button")
    private WebElement modalButton;

    public chairsWebPage(WebDriver driver ){
        this.driver = driver;
        driver.get(URL);
        PageFactory.initElements(driver, this);

    }

    //This function will return a list of all sold out items
    public List<Integer> findOutOfStockItems() {
        List<Integer> outOfStockItems = new ArrayList<Integer>();
        if(items.size()>0){
            for(int i =0;i<items.size();i++){
                if(status.get(i).getText().equalsIgnoreCase(outOfStock)){
                    outOfStockItems.add(i);
                }
            }
        }
        return outOfStockItems;

    }

    //This function will return a list of all in stock items
    public List<Integer> findInStockItems() {
        List<Integer> inStockItems = new ArrayList<Integer>();
        if(items.size()>0){
            for(int i =0;i<items.size();i++){
                if(status.get(i).getText().equalsIgnoreCase(inStock)){
                    inStockItems.add(i);
                }
            }
        }
        return inStockItems;

    }

    //This function will verify the functionality of adding sold out items to the cart
    public void addOutOfStockItemsToCart(List<Integer> products) {
        if(products.size()>0){
            int index = products.get(0);
            items.get(index).click();
            Assert.assertEquals(soldOutModal.size(), 1);
            assertTrue(modalTitle.getText().equalsIgnoreCase(outOfStockModalTitle));
            assertTrue(modalContent.findElements(By.tagName("div")).get(1).getText().equalsIgnoreCase(outOfStockModalText));
            assertTrue(modalButton.getText().equalsIgnoreCase(outOfStockModalButtonText));
        }

    }

    //This function is to view the product by UI
    public String viewTheProduct(List<Integer> products, int index) {
        if(products.size()>0){
            items.get(index).click();
            Assert.assertEquals(driver.getCurrentUrl(),URL + "/product/" + Integer.toString(index+1));
        }
        return driver.getCurrentUrl();

    }

}
