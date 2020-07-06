package steps;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import page_elements.*;
import utils.Base;
import utils.HelperMethods;

import java.io.IOException;
import java.util.List;

public class StepDefinitions extends Base {
    private WebDriver driver;
    private HomePage homePage;
    private WebDriverWait webDriverWait;
    private CategoryPage categoryPage;
    private ProductDetailPage productDetailPage;
    private Logger logger = Logger.getLogger(this.getClass());
    private List<WebElement> elements;
    private JavascriptExecutor jse;
    private Actions actions;
    private HeaderElements headerElements;
    private String productName;
    private BasketPage basketPage;

    public StepDefinitions() throws IOException {
        // initialized driver
        driver = initializeDriver();

        // created page objects with initialized driver
        homePage = new HomePage(driver);
        categoryPage = new CategoryPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        headerElements = new HeaderElements(driver);
        basketPage = new BasketPage(driver);

        // additional helpers
        webDriverWait = new WebDriverWait(driver, 30);
        actions = new Actions(driver);
    }
    @Given("^I visit \"([^\"]*)\"$")
    public void navigate_to_Site(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(arg1);
    }

    @When("^I opened the login popup$")
    public void iNavigatedToLoginPage() throws InterruptedException {
        Thread.sleep(2000);
        homePage.getPersonIcon().click();
        Thread.sleep(2000);
        try{
            webDriverWait.until(ExpectedConditions.elementToBeClickable(homePage.getEmailField()));
        }catch (StaleElementReferenceException e){
            System.out.println("stale error");
        }
    }

    @When("^I close the advertising popup if it is displayed$")
    public void iCloseTheAdvertisingPopupIfItIsDisplayed() {
        // There is a indirim fancybox in homepage which is displayed sometimes. So this is a checker method
        if(homePage.getAdPopupCloseButton().isDisplayed()){
            homePage.getAdPopupCloseButton().click();
        }
    }

    @And("^I logged in with following credentials$")
    public void iLoggedInWithFollowingCredentials(DataTable dataTable) {
        List<String> rows = dataTable.asList(String.class);
        String username = rows.get(0);
        String password = rows.get(1);
        homePage.getEmailField().sendKeys(username);
        homePage.getPasswordField().sendKeys(password);
        homePage.getLoginButton().click();
    }

    @Then("^I should be redirected to \"([^\"]*)\"$")
    public void iShouldBeRedirectedTo(String url) {
        webDriverWait.until(ExpectedConditions.urlContains(url));
    }

    @And("^I closed Indirimleri Kacirma Popup$")
    public void iClosedIndirimleriKacirmaPopup() {
        try{
            homePage.getIndirimPopupCloseButton().click();
        }catch (Exception e){
            System.out.println("Indirim popup is not displayed.");
        }
    }

    @And("^I check if all images are displaying properly in following categories$")
    public void iCheckIfAllImagesAreDisplayingProperlyInFollowingCategories(DataTable dataTable) {
        List<String> rows = dataTable.asList(String.class);

        for(String row: rows){
            jse = (JavascriptExecutor) driver;
            Object result = jse.executeScript("return document.readyState;");

            // I am waiting for js to complete in order to get correct results
            HelperMethods.waitForJStoLoad(webDriverWait, driver);
            if(result.equals("complete")){
                elements = categoryPage.getAllButikBanners();
                for(int i = 0; i < elements.size(); i++){
                    try{
                        HelperMethods.isImageVisible(driver, elements.get(i));
                    }catch (Exception e){
                        logger.error( "Image number #" + i + " is not loaded in the " + row + " page");
                    }
                }
            }
            String locator = "//*[text()='" +row + "']";
            driver.findElement(By.xpath(locator)).click();
        }
    }

    @And("^I visit one of the butik Pages$")
    public void iVisitOneOfTheButikPages() {
        elements = categoryPage.getAllButikBanners();
        int randomIndex = (int) (Math.random() * elements.size() - 1);
        elements.get(randomIndex).click();
    }

    @Then("^I verified if all product images are loaded$")
    public void iVerifiedIfAllProductImagesAreLoaded() {
        jse = (JavascriptExecutor) driver;
        Object result = jse.executeScript("return document.readyState;");
        System.out.println("=> The status is : "+result.toString());
        HelperMethods.waitForJStoLoad(webDriverWait, driver);
        if(result.equals("complete")){
            elements = categoryPage.getAllProductImages();
            for(WebElement productImage : elements){
                try{
                    HelperMethods.isImageVisible(driver, productImage);
                }catch (Exception e){
                    logger.error(productImage.getAttribute("src") + " product image is not loaded");
                }
            }
        }
    }

    @And("^I navigated one of the product detail pages$")
    public void iNavigatedOneOfTheProductDetailPages() {
        boolean isPageLoaded = HelperMethods.waitForJStoLoad(webDriverWait, driver);
        if(isPageLoaded){
            elements = categoryPage.getAllProductNames();
            System.out.println(elements.size());
            int randomIndex = (int) (Math.random() * elements.size() - 1);
            elements.get(randomIndex).click();
            productName = productDetailPage.getProductNameInProductDetail().getText();
            System.out.println("Desired product is = " + productName);
        }

    }

    @And("^I add product to the cart$")
    public void iAddProductToTheCart() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productDetailPage.getAddToBasketButton()));
        HelperMethods.scrollToElement(productDetailPage.getAddToBasketButton(), driver);
        try{
            productDetailPage.getAddToBasketButton().click();
        }catch(Exception e){
            jse.executeScript("arguments[0].click();", productDetailPage.getAddToBasketButton());
        }

    }

    @Then("^I verified product is added to basket properly$")
    public void iVerifiedProductIsAddedToBasketProperly() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(headerElements.getBasketIcon()));

        HelperMethods.scrollToElement(headerElements.getBasketIcon(), driver);
        try{
            headerElements.getBasketIcon().click();
        }catch(Exception e){
            jse.executeScript("arguments[0].click();", headerElements.getBasketIcon());
        }

        webDriverWait.until(ExpectedConditions.urlContains("/sepetim#/basket"));

        webDriverWait.until(ExpectedConditions.visibilityOf(basketPage.getProductNameInBasket()));
        String productNameInBasket = basketPage.getProductNameInBasket().getText();

        System.out.println(productNameInBasket + " ..... " + productName);
        Assert.assertTrue(productName.contains(productNameInBasket));
    }

    @After(order = 2, value = "@clear_basket")
    public void clearBasket() throws InterruptedException {
        driver.get("https://www.trendyol.com/sepetim#/basket");
        WebElement deleteProductButton = basketPage.getDeleteProductInBasket();
        while (!basketPage.getEmptyBasketInfoText().isDisplayed()){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteProductButton));
            deleteProductButton.click();
            basketPage.getAcceptDeleteProductButton().click();
            Thread.sleep(2000);
        }
    }

    // Hook for logging out after test
    @After(order = 1, value = "@log_out")
    public void logOut()
    {
        driver.get("https://www.trendyol.com/");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(homePage.getPersonIcon()));
        actions.moveToElement(homePage.getPersonIcon()).build().perform();
        homePage.getLogoutButton().click();
    }

    @After(order = 0)
    public void teardown()
    {

        driver.close();
        driver=null;
    }
}
