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
import project.java_data_driven.pages.Home;
import project.java_data_driven.pages.Login;
import project.java_data_driven.pages.Website;
import java.time.Duration;

public class LogInSuccessfullyAndThenLogOut {
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
    public void LogInToTheWebsite(){
        Credentials credentials = new Credentials();
        Login pageLogin = new Login();
        Home pageHome = new Home();

        new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(pageLogin.loginButton));
        pageLogin.insertData(driver, pageLogin.usernameInput, credentials.getUsername());
        pageLogin.insertData(driver, pageLogin.passwordInput, credentials.getPassword());
        pageLogin.clickButton(driver, pageLogin.loginButton);

        new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(pageHome.userName));
        String expected = pageHome.getUserName(driver);
        Assert.assertEquals(expected, credentials.getExpected());
    }

    @Test(priority = 3)
    public void LogOutFromTheWebsite(){
        Home pageHome = new Home();

        new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(pageHome.logoutButton));
        pageHome.clickButton(driver, pageHome.logoutButton);
        new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOfElementLocated(pageHome.logoutButton));

        Boolean isInvisible;
        try{
            driver.findElement(pageHome.logoutButton).isDisplayed();
            isInvisible = false;
        }
        catch (Exception e){
            isInvisible = true;
            System.out.println("Log Out button is INVISIBLE!!!");
        }
        Assert.assertTrue(isInvisible);
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
