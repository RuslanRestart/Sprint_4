package org.example.PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RentalDetailsPage extends BasePage{
    //Инпут "Когда привезти самокат"
    private By whenToBringScooterInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Селект Календарь
    private By calendar = By.xpath(".//div[@class='react-datepicker__month-container']");
    //Дропдаун "Срок аренды"
    private By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    //Чекбоксы "Цвет самоката"
    private By scooterColorCheckboxes = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']");
    //Инпут "Комментарий для курьера"
    private By commentInput = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Модалка "Хотите оформить заказ?"
    private By orderModal = By.xpath(".//div[@class='Order_Modal__YZ-d3']");
    //Кнопка "Да" в модалке оформления заказа
    private By yesFromModalButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Модалка об успешном оформлении заказа
    private By orderSuccessModalHeader = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public RentalDetailsPage(WebDriver driver){
        super(driver);
    }

    public boolean checkOrderModalIsDisplayed(){
        return driver.findElement(orderModal).isDisplayed();
    }

    public boolean checkSuccessModalIsDisplayed(){
        return driver.findElement(orderSuccessModalHeader).isDisplayed();
    }

    public void acceptOrder(){
        if (checkOrderModalIsDisplayed()){
            WebElement yesButtonElement = driver.findElement(yesFromModalButton);
            if (yesButtonElement.isEnabled()){
                yesButtonElement.click();
            }
        }
    }

    public void setWhenToBringScooter(String date){
        WebElement whenToBringScooterElement = driver.findElement(whenToBringScooterInput);
        if (whenToBringScooterElement.isEnabled()){
            whenToBringScooterElement.click();
            if (driver.findElement(calendar).isEnabled()){
                WebElement dateElement = driver.findElement(By.xpath(String.format(".//div[@aria-label='Choose %s']", date)));
                if (dateElement.isEnabled()){
                    dateElement.click();
                }
            }
        }
    }

    public void setRentalPeriodDropdown(String rentalPeriod){
        WebElement rentalPeriodDropdownElement = driver.findElement(rentalPeriodDropdown);
        if (rentalPeriodDropdownElement.isEnabled()){
            rentalPeriodDropdownElement.click();
            WebElement periodElement = driver.findElement(By.xpath(String.format(".//div[text()='%s']", rentalPeriod)));
            if (periodElement.isEnabled()){
                periodElement.click();
            }
        }
    }

    public void setScooterColor(String scooterColor){
        if (driver.findElement(scooterColorCheckboxes).isDisplayed()){
            WebElement scooterColorElement = null;
            if (scooterColor.equals("серая безысходность")){
                scooterColorElement = driver.findElement(By.xpath(".//input[@id='grey']"));
            } else if (scooterColor.equals("чёрный жемчуг")) {
                scooterColorElement = driver.findElement(By.xpath(".//input[@id='black']"));
            }
            if (scooterColorElement != null && scooterColorElement.isEnabled()){
                scooterColorElement.click();
            }
        }
    }

    public void setComment(String comment){
        WebElement commentInputElement = driver.findElement(commentInput);
        if (commentInputElement.isEnabled()){
            commentInputElement.clear();
            commentInputElement.sendKeys(comment);
        }
    }

    public void clickOrderButton(){
        WebElement orderButtonElement = driver.findElement(orderButton);
        if (orderButtonElement.isEnabled()){
            orderButtonElement.click();
        }
    }

    public void setRentalDetailsData(String date, String rentalPeriod, String scooterColor, String comment){
        setWhenToBringScooter(date);
        setRentalPeriodDropdown(rentalPeriod);
        setScooterColor(scooterColor);
        setComment(comment);
        clickOrderButton();
    }
}
