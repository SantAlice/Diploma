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

    @Test //Обычная покупка с невалидным месяцем
    void paymentWithWrongMonthApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("09");
        PaymentPage.setCardYear("23");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkExpiredMessage();
    }

    @Test //Обычная покупка с невалидным, пустой месяц
    void paymentWithEmptyMonthApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("");
        PaymentPage.setCardYear("23");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test //Обычная покупка с невалидным годом
    void paymentWithWrongYearApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("21");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkExpiredMessage();
    }

    @Test //Обычная покупка с невалидным годом, пустой год
    void paymentWithEmptyYearApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test //Обычная покупка с невалидным ФИ, состоящим из одного символа
    void paymentWithWrongNameApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("A");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с невалидным ФИ, цифры вместо имени
    void paymentWithNumberWrongNameApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("123456789");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с Невалидным ФИ, кириллица
    void paymentWithCyrillicNameApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Анастасия Куликова");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с Невалидным ФИ, пустое ФИ
    void paymentWithEmptyNameApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }


    @Test //Обычная покупка с невалидным cvv, 2 символа
    void paymentWrongCVVApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("99");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с невалидным cvv, буквы
    void paymentWithWrongLiteralCVVApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("abc");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с невалидным cvv, пустое поле
    void paymentWithEmptyCVVApprovedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    // Тут начинаем тестить карту DECLINED

    @Test //Обычная покупка с валидными данными
    void paymentWithDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkErrorMessage();
    }

    @Test //Обычная покупка с невалидным месяцем
    void paymentWithWrongMonthDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("09");
        PaymentPage.setCardYear("23");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkExpiredMessage();
    }

    @Test //Обычная покупка с невалидным, пустой месяц
    void paymentWithEmptyMonthDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("");
        PaymentPage.setCardYear("23");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test //Обычная покупка с невалидным годом
    void paymentWithWrongYearDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("21");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkExpiredMessage();
    }

    @Test //Обычная покупка с невалидным годом, пустой год
    void paymentWithEmptyYearDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test //Обычная покупка с невалидным ФИ, состоящим из одного символа
    void paymentWithWrongNameDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("A");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с невалидным ФИ, цифры вместо имени
    void paymentWithNumberWrongNameDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("123456789");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с Невалидным ФИ, кириллица
    void paymentWithCyrillicNameDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Анастасия Куликова");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с Невалидным ФИ, пустое ФИ
    void paymentWithEmptyNameDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }


    @Test //Обычная покупка с невалидным cvv, 2 символа
    void paymentWrongCVVDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("99");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test //Обычная покупка с невалидным cvv, буквы
    void paymentWithWrongLiteralCVVDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("abc");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test //Обычная покупка с невалидным cvv, пустое поле
    void paymentWithEmptyCVVDeclinedCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test //Обычная покупка с невалидным cvv, пустое поле
    void paymentWithRandomCard() throws SQLException {
        PaymentPage.usualPayment();
        PaymentPage.setCardNumber("1234567812345678");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }


}
