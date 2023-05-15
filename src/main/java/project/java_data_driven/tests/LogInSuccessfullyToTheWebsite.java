package project.java_data_driven.tests;


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
import java.util.Objects;

public class LogInSuccessfullyToTheWebsite {
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

        if (Objects.equals(expected, credentials.getExpected())){
            System.out.println("PASSED!");
        }
        else{
            System.out.println("FAILED!");
        }
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}
