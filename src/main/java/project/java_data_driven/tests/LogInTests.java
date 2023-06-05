package project.java_data_driven.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import project.java_data_driven.credentials.Credentials;
import project.java_data_driven.pages.Home;
import project.java_data_driven.pages.Login;
import project.java_data_driven.pages.Website;

import java.time.Duration;

public class LogInTests {
    WebDriver driver;
    Duration timeout = Duration.ofSeconds(3);

    @Test(dataProvider = "testData")
    public void test(String username, String password, String expectedResult){
        Login pageLogin = new Login();
        Home pageHome = new Home();
        driver = new ChromeDriver();
        new Website().LoadTheWebsite(driver);

        new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(pageLogin.loginButton));
        pageLogin.insertData(driver, pageLogin.usernameInput, username);
        pageLogin.insertData(driver, pageLogin.passwordInput, password);
        pageLogin.clickButton(driver, pageLogin.loginButton);

        if (username.equals("maria") && password.equals("thoushallnotpass")){
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(pageHome.userName));
            String expected = driver.findElement(pageHome.userName).getText();
            Assert.assertEquals(expected, expectedResult);
            driver.close();
        }
        else{
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(pageLogin.errorMessage));
            String expectedErrorMessage = driver.findElement(pageLogin.errorMessage).getText();
            Assert.assertEquals(expectedErrorMessage, expectedResult);
            driver.close();
        }
    }

    @AfterTest
    public void closeBrowser() {driver.quit();}

    @DataProvider(name = "testData")
    public Object[][] getData(){
        Object data[][] = testData();
        return data;
    }

    public static Object[][] testData(){
        Credentials excel = new Credentials();
        int rowCount = excel.getRowCount();
        int columnCount = excel.getColumnCount();

        Object data[][] = new Object[rowCount - 1][columnCount];

        for(int i = 1; i < rowCount; i++){
            for(int j = 0; j < columnCount; j++){

                String cellData = excel.getCellDataString(i, j);
                //System.out.print(cellData + " | ");
                data[i-1][j] = cellData;
            }
            //System.out.println();
        }
        return data;
    }
}
