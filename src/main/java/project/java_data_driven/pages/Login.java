package project.java_data_driven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Login {

    public By usernameInput = By.id("username");
    public By passwordInput = By.id("password");
    public By loginButton = By.xpath("//button[@type='submit']");
    public By errorMessage = By.className("alert-warning");

    public WebDriver insertData(WebDriver driver, By element, String data) {
        WebElement usernameInput = driver.findElement(element);
        usernameInput.click();
        usernameInput.sendKeys(data);
        return driver;
    }

    public WebDriver clickButton(WebDriver driver, By element) {
        WebElement submitButton = driver.findElement(element);
        submitButton.click();
        return driver;
    }
}