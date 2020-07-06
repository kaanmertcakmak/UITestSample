package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderElements {

    private WebDriver webDriver;

    public HeaderElements(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By basketIcon = By.cssSelector(".navigation-icon-basket");
    private By productNameInMiniBasket = By.cssSelector(".productInfoBox .productName");
    private By sepeteGitButtonInMiniBasket = By.cssSelector(".goBasket");

    public WebElement getBasketIcon(){
        return webDriver.findElement(basketIcon);
    }

    public WebElement getProductNameInMiniBasket(){
        return webDriver.findElement(productNameInMiniBasket);
    }

    public WebElement getSepeteGitButtonInMiniBasket(){
        return webDriver.findElement(sepeteGitButtonInMiniBasket);
    }
}
