import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BDTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void clearAll() throws SQLException {
        DBHelper.clearData();
    }

    //Проверка статуса платежа в БД при валидной карте
    @Test
    void PaymentWithValidCardDB() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkSuccessMessage();
        assertEquals("APPROVED", DBHelper.checkPaymentStatus());
    }

    //Проверка статуса платежа в БД при невалидной карте
    @Test
    void PaymentWithInValidCardDB() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkErrorMessage();
        assertEquals("DECLINED", DBHelper.checkPaymentStatus());
    }

    //Проверка статуса кредита в БД при валидной карте
    @Test
    void CreditWithValidCardDB() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkSuccessMessage();
        assertEquals("APPROVED", DBHelper.checkCreditStatus());
    }

    //Проверка статуса кредита в БД при невалидной карте
    @Test
    void CreditWithInValidCardDB() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkErrorMessage();
        assertEquals("DECLINED", DBHelper.checkCreditStatus());
    }

    //Проверка статуса кредита в БД при несуществующей карте
    @Test
    void CreditWithNotExistingCardDB() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("1234567890123456");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkErrorMessage();
        assertEquals("DECLINED", DBHelper.checkCreditStatus());
    }

    //Проверка статуса платежа в БД при несуществующей карте
    @Test
    void PaymentWithNotExistingCardDB() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("1234567890123456");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkErrorMessage();
        assertEquals("DECLINED", DBHelper.checkPaymentStatus());
    }

}
