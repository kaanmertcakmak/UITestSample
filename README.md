# **Trendyol Sample UI Test**

This is a sample selenium ui automation framework.

`**REQUIREMENTS**`

- Maven 3
- Java 8

In order to make things work, you need to install libraries in pom.xml

**After that to run the test case:**

Run `mvn test` in command line. This will run tests in chrome browser since it is the default browser.

In order to choose browser:
Run `mvn -Dbrowser=firefox test`. This will run tests in Firefox

**ARCHITECTURE**

./testngCucumber.xml

Cucumber settings as xml

----------------------------------

src > main > java > utils > Base

In this class there is initialize driver method which does set up WebDriver

-----------------------------------

./src/main/java/utils/HelperMethods

In this class there are helper methods executes javascript when needed.

-----------------------------------
In ./features package there is feature file which includes Cucumber scenario

-----------------------------------

In ./page_elements package there are Page classes that contains page's locators and web elements

------------------------------------

In ./steps package there is StepDefinitions class which contains step definitions of cucumber scenarios

------------------------------------

In ./Logs/test-project.log file which contains log messages if butik or product images are not displayed