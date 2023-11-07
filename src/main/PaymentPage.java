import com.codeborne.selenide.SelenideElement;
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
}