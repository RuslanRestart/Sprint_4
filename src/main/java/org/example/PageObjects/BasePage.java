package org.example.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;
    //Логотип самоката
    protected By scooterLogoButton = By.xpath(".//a[@class='Header_LogoScooter__3lsAR']");
    //Логотип яндекса
    protected By yandexLogoButton = By.xpath(".//a[@class='Header_LogoYandex__3TSOI']");
    //кнопка "Статус заказа"
    protected By orderStatusButton = By.xpath(".//button[@class='Header_Link__1TAG7']");
    //Инпут "Введите номер заказа"
    protected By orderNumberInput = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    //Кнопка "Go!"
    protected By goButton = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");
    public static final String YANDEX_DZEN_URL = "https://dzen.ru/?yredirect=true";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickScooterLogo() {
        WebElement scooterLogoElement = driver.findElement(scooterLogoButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scooterLogoElement);
        if (scooterLogoElement.isEnabled()) {
            scooterLogoElement.click();
        }
    }

    public void clickYandexLogo() {
        WebElement yandexLogoElement = driver.findElement(yandexLogoButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", yandexLogoElement);
        if (yandexLogoElement.isEnabled()) {
            yandexLogoElement.click();
        }
    }

    public String getYandexNewTabUrl() {
        String url = "";
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        if (allWindows.size() > 1) {
            for (String window : allWindows) {
                if (!window.equals(originalWindow)) {
                    url = driver.switchTo().window(window).getCurrentUrl();
                    break;
                }
            }
        }
        driver.switchTo().window(originalWindow);
        return url;
    }

    public void clickOrderStatusButton() {
        WebElement orderStatusElement = driver.findElement(orderStatusButton);
        if (orderStatusElement.isEnabled()) {
            orderStatusElement.click();
        }
    }

    public void clickGoButton() {
        WebElement goButtonElement = driver.findElement(goButton);
        if (goButtonElement.isEnabled()) {
            goButtonElement.click();
        }
    }

    public void enterOrderNumber(String orderNumber) {
        clickOrderStatusButton();
        WebElement orderNumberInputElement = driver.findElement(orderNumberInput);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
        orderNumberInputElement.clear();
        orderNumberInputElement.sendKeys(orderNumber);
        clickGoButton();
    }
}
