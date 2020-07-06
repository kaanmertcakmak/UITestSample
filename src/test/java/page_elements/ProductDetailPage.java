package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailPage {
    private WebDriver webDriver;
    public ProductDetailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By addToBasketButton = By.cssSelector(".add-to-bs");
    private By productNameInProductDetail = By.cssSelector(".pr-nm");

    public WebElement getAddToBasketButton(){
        return webDriver.findElement(addToBasketButton);
    }

    public WebElement getProductNameInProductDetail(){
        return webDriver.findElement(productNameInProductDetail);
    }
}
