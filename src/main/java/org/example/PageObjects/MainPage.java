package org.example.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage extends BasePage{
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";
    //кнопка "Заказать" вверху страницы
    private By topOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //Кнопка "Заказать" внизу страницы
    private By bottomOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Кнопка Принятия куки
    private By acceptCookieButton = By.xpath(".//button[@id='rcc-confirm-button']");
    //Локатор вопроса о важном
    private String questionLink = ".//div[@id = 'accordion__heading-%d']";
    //Локатор ответа на вопрос о важном
    private String answerLink = ".//div[@id='accordion__panel-%d']/p";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookie(){
        WebElement acceptCookieElement = driver.findElement(acceptCookieButton);
        if (acceptCookieElement.isEnabled()){
            acceptCookieElement.click();
        }
    }

    public void clickQuestion(int index){
        WebElement questionElement = driver.findElement(By.xpath(String.format(questionLink, index)));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(questionElement));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        if (questionElement.isEnabled()){
            questionElement.click();
        }
    }

    public String getAnswer(int index){
        clickQuestion(index);
        WebElement answerElement = driver.findElement(By.xpath(String.format(answerLink, index)));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(answerElement));
        return answerElement.getText();
    }

    public void clickTopOrderButton(){
        WebElement element = driver.findElement(topOrderButton);
        if (element.isEnabled()){
            element.click();
        }
    }

    public void clickBottomOrderButton(){
        WebElement buttomOrderButtonElemennt = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", buttomOrderButtonElemennt);
        if (buttomOrderButtonElemennt.isEnabled()){
            buttomOrderButtonElemennt.click();
        }
    }
}
