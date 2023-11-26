import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;


import java.io.FileNotFoundException;
import java.sql.SQLException;

public class CreditTest {

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

    @Test
        //Обычная покупка с валидными данными
    void creditWithApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkSuccessMessage();
    }

    @Test
        //Обычная покупка с невалидным месяцем
    void creditWithWrongMonthApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("09");
        PaymentPage.setCardYear("23");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkExpiredMessage();
    }

    @Test
        //Обычная покупка с невалидным, пустой месяц
    void creditWithEmptyMonthApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("");
        PaymentPage.setCardYear("23");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test
        //Обычная покупка с невалидным годом
    void creditWithWrongYearApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("21");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkExpiredMessage();
    }

    @Test
        //Обычная покупка с невалидным годом, пустой год
    void creditWithEmptyYearApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test
        //Обычная покупка с невалидным ФИ, состоящим из одного символа
    void creditWithWrongNameApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("A");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с невалидным ФИ, цифры вместо имени
    void creditWithNumberWrongNameApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("123456789");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с Невалидным ФИ, кириллица
    void creditWithCyrillicNameApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Анастасия Куликова");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с Невалидным ФИ, пустое ФИ
    void creditWithEmptyNameApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }


    @Test
        //Обычная покупка с невалидным cvv, 2 символа
    void creditWrongCVVApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("99");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с невалидным cvv, буквы
    void creditWithWrongLiteralCVVApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("abc");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с невалидным cvv, пустое поле
    void creditWithEmptyCVVApprovedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444441");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("");
        PaymentPage.setCardCVV("Vasily Alekseev");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    // Тут начинаем тестить карту DECLINED

    @Test
        //Обычная покупка с валидными данными
    void creditWithDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("03");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkErrorMessage();
    }

    @Test
        //Обычная покупка с невалидным месяцем
    void creditWithWrongMonthDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("09");
        PaymentPage.setCardYear("23");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkExpiredMessage();
    }

    @Test
        //Обычная покупка с невалидным, пустой месяц
    void creditWithEmptyMonthDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("");
        PaymentPage.setCardYear("23");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test
        //Обычная покупка с невалидным годом
    void creditWithWrongYearDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("21");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkExpiredMessage();
    }

    @Test
        //Обычная покупка с невалидным годом, пустой год
    void creditWithEmptyYearDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("");
        PaymentPage.setCardOwner("Vasiliy Alekseev");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test
        //Обычная покупка с невалидным ФИ, состоящим из одного символа
    void creditWithWrongNameDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("A");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с невалидным ФИ, цифры вместо имени
    void creditWithNumberWrongNameDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("123456789");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с Невалидным ФИ, кириллица
    void creditWithCyrillicNameDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Анастасия Куликова");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с Невалидным ФИ, пустое ФИ
    void creditWithEmptyNameDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("");
        PaymentPage.setCardCVV("999");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }


    @Test
        //Обычная покупка с невалидным cvv, 2 символа
    void creditWrongCVVDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("99");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

    @Test
        //Обычная покупка с невалидным cvv, буквы
    void creditWithWrongLiteralCVVDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("Vasily Alekseev");
        PaymentPage.setCardCVV("abc");
        PaymentPage.pushContinueButton();
        PaymentPage.checkWrongFormatMessage();
    }

    @Test
        //Обычная покупка с невалидным cvv, пустое поле
    void creditWithEmptyCVVDeclinedCard() throws SQLException {
        PaymentPage.creditPayment();
        PaymentPage.setCardNumber("4444444444444442");
        PaymentPage.setCardMonth("04");
        PaymentPage.setCardYear("24");
        PaymentPage.setCardOwner("");
        PaymentPage.setCardCVV("Vasily Alekseev");
        PaymentPage.pushContinueButton();
        PaymentPage.checkEmptyMessage();
    }

}
