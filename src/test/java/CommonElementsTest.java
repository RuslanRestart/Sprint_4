import org.example.PageObjects.BasePage;
import org.example.PageObjects.MainPage;
import org.example.PageObjects.OrderPage;
import org.example.PageObjects.TrackPage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonElementsTest extends BaseTest {

    @Test
    public void enterWrongOrderNumberTest(){
        BasePage page = new BasePage(driver);
        page.enterOrderNumber("1234567890");
        assertTrue("Заглушка с отсутствующим заказом отсутствует!", new TrackPage(driver).isTrackNotFoundDisplayed());
    }

    @Test
    public void clickScooterLogoTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickTopOrderButton();
        assertEquals("Текущий url отличается от ожидаемого!", OrderPage.ORDER_PAGE_URL, driver.getCurrentUrl());
        mainPage.clickScooterLogo();
        assertEquals("Текущий url отличается от ожидаемого!", MainPage.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    public void clickYandexLogoTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickBottomOrderButton();
        assertEquals("Текущий url отличается от ожидаемого!", OrderPage.ORDER_PAGE_URL, driver.getCurrentUrl());
        mainPage.clickYandexLogo();
        assertEquals("Текущий url отличается от ожидаемого!", BasePage.YANDEX_DZEN_URL, mainPage.getYandexNewTabUrl());
    }
}
