package project.java_data_driven.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import project.java_data_driven.credentials.Credentials;
import project.java_data_driven.pages.Home;
import project.java_data_driven.pages.Login;
import project.java_data_driven.pages.Website;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import java.time.Duration;

public class Tests {
    WebDriver driver;

    @BeforeTest
    public void loadTheHomePage(){
        // Will be run once before all other Tests i.e. they will then inherit the driver
        driver = new ChromeDriver();
        new Website().LoadTheWebsite(driver);
    }

    @Test (priority = 1)
    public void testLogin(){
        // Create credentials and pages objects
        Credentials credentials = new Credentials();

        Login pageLogin = new Login();
        Home pageHome = new Home();

        // Wait for the login form to load
        WebElement waitForLoginPage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(pageLogin.submitButton));
        // Submit the login data
        pageLogin.insertData(driver, pageLogin.usernameInput, credentials.getUsername());
        pageLogin.insertData(driver, pageLogin.passwordInput, credentials.getPassword());
        pageLogin.clickButton(driver, pageLogin.submitButton);
        // Wait for the home page to load
        WebElement waitForHomePage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(pageHome.userName));
        // Get the logged user name and assert it against the expected
        String expected = pageHome.getUserName(driver);
        Assert.assertEquals(expected, credentials.getExpected());
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
