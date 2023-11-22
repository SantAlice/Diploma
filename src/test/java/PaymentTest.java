import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;


import java.io.FileNotFoundException;
import java.sql.SQLException;

public class PaymentTest {

    private PaymentPage paymentPage;

    @BeforeEach
    void setUpPage() throws FileNotFoundException {
        paymentPage = new PaymentPage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clearAll() throws SQLException {
        DBHelper.clearData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test //Обычная покупка с валидными данными
    void paymentWithApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkSuccessMessage();
    }

}
