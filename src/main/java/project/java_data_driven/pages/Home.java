package project.java_data_driven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Home {

    public By userName = By.className("username");
    public By logoutButton = By.id("logout");

    public String getUserName(WebDriver driver) {
        WebElement username = driver.findElement(userName);
        String title = username.getText();
        return title;
    }

    public WebDriver clickButton(WebDriver driver, By element) {
        WebElement logoutButton = driver.findElement(element);
        logoutButton.click();
        return driver;
    }
}
