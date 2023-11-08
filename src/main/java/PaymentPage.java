import com.codeborne.selenide.SelenideElement;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class PaymentPage {
    String url = System.getProperty("db.url");
    String appURL = System.getProperty("app.url");
    String appPORT = System.getProperty("app.port");
    String userDB = System.getProperty("app.userDB");
    String password = System.getProperty("app.password");


    List<SelenideElement> inputFields = $$(".input__control");

    SelenideElement cardNumber = inputFields.get(0);
    SelenideElement month = inputFields.get(1);
    SelenideElement year = inputFields.get(2);
    SelenideElement cardOwner = inputFields.get(3);
    SelenideElement cvcOrCvvNumber = inputFields.get(4);

    public void usualPay() {
        open(appURL + ":" + appPORT);
        $$(".button__content").find(exactText("Купить")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Оплата по карте")).shouldBe(visible);
    }

    public void buyForYourMoney() {
        open(appURL + ":" + appPORT);
        $$(".button__content").find(exactText("Купить в кредит")).click();
        $$(".heading_theme_alfa-on-white").find(exactText("Кредит по данным карты")).shouldBe(visible);
    }
}

