import org.example.PageObjects.OrderPage;
import org.example.PageObjects.MainPage;
import org.example.PageObjects.RentalDetailsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPageTest extends BaseTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;

    public OrderPageTest(String name, String surname, String address, String metroStation, String phoneNumber,
                         String date, String rentalPeriod, String scooterColor, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Руслан", "Ерофеев", "3-й Нижнелихоборовский проезд 4а", "Воробьёвы горы", "+79279080140", "суббота, 30-е ноября 2024 г.",
                        "шестеро суток", "чёрный жемчуг", "17-й этаж, квартира 255. Поворот налево от лифта"},
                {"Вася", "Пупкин", "Вязовая 2", "Окружная", "+88005553535", "воскресенье, 1-е декабря 2024 г.",
                        "двое суток", "серая безысходность", "1-й этаж, постучать в дверь"}
        };
    }

    @Test
    public void doOrderFromTopButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookie();
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderPage(name, surname, address, metroStation, phoneNumber);

        RentalDetailsPage rentalDetailsPage = new RentalDetailsPage(driver);
        rentalDetailsPage.setRentalDetailsData(date, rentalPeriod, scooterColor, comment);
        rentalDetailsPage.acceptOrder();

        assertTrue("Модальное окно об успешном создании заказа отсутствует!", rentalDetailsPage.checkSuccessModalIsDisplayed());
    }

    @Test
    public void doOrderFromBottomButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookie();
        mainPage.clickBottomOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderPage(name, surname, address, metroStation, phoneNumber);

        RentalDetailsPage rentalDetailsPage = new RentalDetailsPage(driver);
        rentalDetailsPage.setRentalDetailsData(date, rentalPeriod, scooterColor, comment);
        rentalDetailsPage.acceptOrder();

        assertTrue("Модальное окно об успешном создании заказа отсутствует!", rentalDetailsPage.checkSuccessModalIsDisplayed());
    }

    @Test
    public void checkErrorsOrderFieldsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookie();
        mainPage.clickTopOrderButton();
        OrderPage orderPage = new OrderPage(driver);

        orderPage.setName("Ruslan");
        orderPage.setSurname("Салтыков-Щедрин");
        orderPage.setAddress("ИК-1");
        orderPage.setPhoneNumber("8800-555-35-35");

        orderPage.clickOnNextButton();

        assertTrue("Name error not displayed!", orderPage.isNameInputErrorDisplayed());
        assertTrue("Surname error not displayed!", orderPage.isSurnameInputErrorDisplayed());
        assertTrue("Address error not displayed!", orderPage.isAddressInputErrorDisplayed());
        assertTrue("MetroStation error not displayed!", orderPage.isMetroStationInputErrorDisplayed());
        assertTrue("PhoneNumber error not displayed!", orderPage.isPhoneNumberInputErrorDisplayed());
    }
}
