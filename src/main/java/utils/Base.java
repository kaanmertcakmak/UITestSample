package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver initializeDriver() {

        String browserName = System.getProperty("browser");

        WebDriver webDriver;
        if(browserName == null || browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            webDriver = new ChromeDriver(chromeOptions);
        }else{
            System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            webDriver = new FirefoxDriver(firefoxOptions);
        }

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }
}
