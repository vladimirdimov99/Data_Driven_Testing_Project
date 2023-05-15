package project.java_data_driven.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import project.java_data_driven.credentials.Credentials;
import project.java_data_driven.pages.Login;
import project.java_data_driven.pages.Website;

import java.time.Duration;

public class LogInWithInvalidCredentials {
    WebDriver driver;
    String currentUrl;
    String expectedUrl;
    Duration timeout = Duration.ofSeconds(3);

    @BeforeTest
    public void loadTheHomePage(){
        driver = new ChromeDriver();
        new Website().LoadTheWebsite(driver);
    }

    @Test(priority = 1)
    public void checkIfTheWebsiteUrlIsCorrect(){
        currentUrl = driver.getCurrentUrl();
        expectedUrl = "https://robotsparebinindustries.com/#/";
        Assert.assertEquals(currentUrl, expectedUrl);
    }

    @Test (priority = 2)
    public void LogInToTheWebsiteWithInvalidCredentials(){
        Credentials credentials = new Credentials();
        Login pageLogin = new Login();

        new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(pageLogin.loginButton));
        pageLogin.insertData(driver, pageLogin.usernameInput, credentials.getInvalidUsername());
        pageLogin.insertData(driver, pageLogin.passwordInput, credentials.getInvalidPassword());
        pageLogin.clickButton(driver, pageLogin.loginButton);

        new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(pageLogin.errorMessage));
        String expectedErrorMessage = driver.findElement(pageLogin.errorMessage).getText();
        Assert.assertEquals(expectedErrorMessage, credentials.getExpectedErrorMessage());
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
