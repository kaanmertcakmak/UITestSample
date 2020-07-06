package page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver webDriver;
    private By personIcon = By.id("accountBtn");
    private By advertisingPopupCloseButton = By.cssSelector(".fancybox-close");
    private By emailFieldInLoginPopup = By.id("email");
    private By passwordFieldInLoginPopup = By.id("password");
    private By loginButton = By.id("loginSubmit");
    private By indirimPopupCloseButton = By.cssSelector("div#notification-popup .modal-close");
    private By logoutButton = By.cssSelector(".icon-logout");

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public WebElement getPersonIcon() {
        return webDriver.findElement(personIcon);
    }

    public WebElement getAdPopupCloseButton(){
        return webDriver.findElement(advertisingPopupCloseButton);
    }

    public WebElement getEmailField(){
        return webDriver.findElement(emailFieldInLoginPopup);
    }

    public WebElement getPasswordField(){
        return webDriver.findElement(passwordFieldInLoginPopup);
    }

    public WebElement getLoginButton(){
        return webDriver.findElement(loginButton);
    }

    public WebElement getIndirimPopupCloseButton(){
        return webDriver.findElement(indirimPopupCloseButton);
    }

    public WebElement getLogoutButton(){
        return webDriver.findElement(logoutButton);
    }
}
