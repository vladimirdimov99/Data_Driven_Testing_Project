package project.java_data_driven.pages;

import org.openqa.selenium.WebDriver;

public class Website {
    private String website = "https://robotsparebinindustries.com/#/";

    public void LoadTheWebsite (WebDriver driver) {
        driver.get(website);
        driver.manage().window().maximize();
    }
}
