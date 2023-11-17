import com.codeborne.selenide.SelenideElement;

import java.io.*;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.System.getProperty;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;


public class PaymentPage {
    static Properties props = new Properties();

    static {
        try (InputStream inputStream = new FileInputStream("application.properties")) {
            props.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    //Получаем значения свойств
    private static String url = getProperty("db.url");
    private static String appURL = props.getProperty("appURL");
    private static String appPORT = props.getProperty("appPORT");
    private static String userDB = getProperty("app.userDB");
    private static String password = getProperty("app.password");

    //Получаем элементы со сторницы по индкесу
    static List<SelenideElement> inputFields = $$(".input__control");

    static SelenideElement cardNumber = inputFields.get(0);
    static SelenideElement month = inputFields.get(1);
    static SelenideElement year = inputFields.get(2);
    static SelenideElement cardOwner = inputFields.get(3);
    static SelenideElement cvcOrCvvNumber = inputFields.get(4);

    public PaymentPage() throws FileNotFoundException {
    }
    //Находим кнопку "Купить" и кликаем на неё
    public static void usualPayment() {
        open(appURL +":"+appPORT);
        $$(".button__content").find(exactText("Купить")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Оплата по карте")).shouldBe(visible);
    }
    //Находим кнопку "Купить в кредит" и кликаем на неё
    public static void creditPayment() {
        open(appURL + ":" + appPORT);
        $$(".button__content").find(exactText("Купить в кредит")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Кредит по данным карты")).shouldBe(visible);
    }
    //Проверяем статус успешной покупки
    public static void checkSuccessMessage() {
        $$(".notification__title")
                .find(exactText("Успешно"))
                .shouldBe(visible, Duration.ofSeconds(15));
    }
    //Проверяем статус неуспешной покупки
    public void checkErrorMessage() {
        $$(".notification__title")
                .find(exactText("Ошибка"))
                .shouldBe(visible, Duration.ofSeconds(15));
    }
    //Проверяем оповещение об ошибке поля
    public void checkWrongFormatMessage() {
        $$(".input__sub")
                .find(exactText("Неверный формат"))
                .shouldBe(visible);
    }
    //Проверяем оповещение об ошибке поля
    public void checkExpiredMessage() {
        $$(".input__sub")
                .find(exactText("Истёк срок действия карты"))
                .shouldBe(visible);
    }
    //Проверяем оповещение об ошибке поля
    public void checkEmptyMessage() {
        $$(".input__sub")
                .find(exactText("Поле обязательно для заполнения"))
                .shouldBe(visible);
    }
    //Устанавливаем значения в поля карт (вручную в тестах)
    public static void setCardNumber(String cNumber) {
        cardNumber.setValue(cNumber);
    }

    public static void setCardMonth(String cMonth) {
        month.setValue(cMonth);
    }

    public static void setCardYear(String cYear) {
        year.setValue(cYear);
    }

    public static void setCardOwner(String cOwner) {
        cardOwner.setValue(cOwner);
    }

    public static void setCardCVV(String cCvv) {
        cvcOrCvvNumber.setValue(cCvv);
    }

    //Находим кнопку "Продолжить" и кликаем на неё
    private static SelenideElement findButtonByText(String buttonText) {
        return $$(".button__content")
                .find(exactText(buttonText))
                .shouldBe(visible);
    }
    public static void pushContinueButton() {
        findButtonByText("Продолжить").click();
    }


}

