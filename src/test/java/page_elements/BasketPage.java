package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasketPage {

    private WebDriver webDriver;

    public BasketPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By productNameInBasket = By.cssSelector(".basketlist-productinfo-description");
    private By productNameInBasket2 = By.cssSelector(".pb-item span");
    private By deleteProductInBasket = By.cssSelector(".removeitem");
    private By deleteProductInBasket2 = By.cssSelector(".i-trash");
    private By acceptDeleteProductButton = By.cssSelector(".btn-remove");
    private By emptyBasketInfoText = By.cssSelector(".emptyBasketInfoBox span");

    public WebElement getProductNameInBasket(){
        try{
            return webDriver.findElement(productNameInBasket);
        }catch(Exception e){
            return webDriver.findElement(productNameInBasket2);
        }

    }

    public WebElement getDeleteProductInBasket(){
        try{
            return webDriver.findElement(deleteProductInBasket);
        }catch(Exception e){
            return webDriver.findElement(deleteProductInBasket2);
        }

    }

    public WebElement getAcceptDeleteProductButton(){
        return webDriver.findElement(acceptDeleteProductButton);
    }

    public WebElement getEmptyBasketInfoText(){
        return webDriver.findElement(emptyBasketInfoText);
    }
}
