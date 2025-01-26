package org.example.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TrackPage extends BasePage{
    private By trackNotFound = By.xpath(".//div[@class='Track_NotFound__6oaoY']");

    public TrackPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTrackNotFoundDisplayed(){
        return driver.findElement(trackNotFound).isDisplayed();
    }

}
