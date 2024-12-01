package org.example.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class OrderPage extends BasePage {
    public static final String ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";
    //инпут "Имя"
    private By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    //инпут "Фамилия"
    private By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    //инпут "Адрес"
    private By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //инпут "Станция метро"
    private By metroStationInput = By.className("select-search__input");
    //Инпут "Телефон"
    private By phoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локаторы ошибок
    //Локатор ошибки при вводе имени
    private By nameInputError = By.xpath(".//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректное имя']");
    //Локатор ошибки при вводе фамилии
    private By surnameInputError = By.xpath(".//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректную фамилию']");
    //Локатор ошибки при вводе адреса
    private By addressInputError = By.xpath(".//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректный адрес']");
    //Локатор ошибки при пропуске ввода метро
    private By metroStationInputError = By.xpath(".//div[@class='Order_MetroError__1BtZb' and text()='Выберите станцию']");
    //Локатор ошибки при вводе телефона
    private By phoneNumberInputError = By.xpath(".//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректный номер']");

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void setName(String name){
        WebElement nameElement = driver.findElement(nameInput);
        nameElement.clear();
        nameElement.sendKeys(name);
    }

    public void setSurname(String surname){
        WebElement nameElement = driver.findElement(surnameInput);
        nameElement.clear();
        nameElement.sendKeys(surname);
    }

    public void setAddress(String address){
        WebElement addressElement = driver.findElement(addressInput);
        addressElement.clear();
        addressElement.sendKeys(address);
    }
    
    public void setMetroStation(String metroStationName){
        WebElement metroStationElement = driver.findElement(metroStationInput);
        if (metroStationElement.isEnabled()){
            metroStationElement.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            String metroStationNamePattern = ".//div[text()='%s']";
            WebElement metroStationNameElement = driver.findElement(By.xpath(String.format(metroStationNamePattern, metroStationName)));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", metroStationNameElement);
            if (metroStationElement.isEnabled()){
                metroStationNameElement.click();
            }
        }
    }

    public void setPhoneNumber(String phoneNumber){
        WebElement phoneNumberElement = driver.findElement(phoneNumberInput);
        phoneNumberElement.clear();
        phoneNumberElement.sendKeys(phoneNumber);
    }

    public void clickOnNextButton(){
        WebElement nextButtonElement = driver.findElement(nextButton);
        if (nextButtonElement.isEnabled()){
            nextButtonElement.click();
        }
    }

    public void fillOrderPage(String name, String surname, String address, String metroStation, String phoneNumber){
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phoneNumber);
        clickOnNextButton();
    }

    public boolean isNameInputErrorDisplayed(){
        return driver.findElement(nameInputError).isDisplayed();
    }

    public boolean isSurnameInputErrorDisplayed(){
        return driver.findElement(surnameInputError).isDisplayed();
    }

    public boolean isAddressInputErrorDisplayed(){
        return driver.findElement(addressInputError).isDisplayed();
    }

    public boolean isMetroStationInputErrorDisplayed(){
        return driver.findElement(metroStationInputError).isDisplayed();
    }

    public boolean isPhoneNumberInputErrorDisplayed(){
        return driver.findElement(phoneNumberInputError).isDisplayed();
    }
}
