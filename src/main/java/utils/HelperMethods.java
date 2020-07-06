package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperMethods {



    public static boolean isImageVisible(WebDriver webDriver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return (boolean) js.executeScript("return arguments[0].complete && (typeof arguments[0].naturalWidth !== \"undefined\") && (arguments[0].naturalWidth > 0)", element);
    }

    public static boolean waitForJStoLoad(WebDriverWait wait, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jsLoad);
    }

    public static void scrollToElement(WebElement webElement, WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
}
