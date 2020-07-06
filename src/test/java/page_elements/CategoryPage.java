package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryPage {

    private WebDriver webDriver;

    public CategoryPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    private By butikBanner = By.cssSelector("a[data-type='banner'] img");
    private By productImage = By.cssSelector(".short-product-image");
    private By productName = By.cssSelector(".boutique-product .brand");

    public List<WebElement> getAllButikBanners() {
        return webDriver.findElements(butikBanner);
    }

    public List<WebElement> getAllProductImages(){
        return webDriver.findElements(productImage);
    }

    public List<WebElement> getAllProductNames(){
        return webDriver.findElements(productName);
    }


}
