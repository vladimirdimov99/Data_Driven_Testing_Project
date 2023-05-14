package project.java_data_driven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Home {

    public By userName = By.xpath("//span[@class='username']");

    public String getUserName(WebDriver driver) {
        WebElement username = driver.findElement(userName);
        String title = username.getText();
        return title;
    }
}
