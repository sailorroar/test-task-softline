import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$x;

public class LoginTest extends BaseTest{

    private final static String BASE_URL = "https://lmslite47vr.demo.mirapolis.ru/mira";
    private final static String VALID_LOGIN = "fominaelena";
    private final static String VALID_PASSWORD = "1P73BP4Z";

    @Test
    public void validLogin() {
        MainPage mainPage = new MainPage();
        mainPage.openWebSite(BASE_URL);
        mainPage.login(VALID_LOGIN, VALID_PASSWORD);
        SelenideElement indentificationVal = $x("//div[@class='mira-notification-info-widget-notifications ']");
        Assert.assertEquals("mira-notification-info-widget-notifications ", indentificationVal.getAttribute("class"));
    }

    @Test
    public void unvalidLogin() {
        MainPage mainPage = new MainPage();
        mainPage.openWebSite(BASE_URL);
        mainPage.login("test", "test");
        WebDriver driver = WebDriverRunner.getWebDriver();

        try {
            driver.getCurrentUrl();
        }catch (UnhandledAlertException e) {
            Assert.assertEquals("Неверные данные для авторизации", e.getAlertText());
        }
    }

    @Test
    public void nullLogin() {
        MainPage mainPage = new MainPage();
        mainPage.openWebSite(BASE_URL);
        mainPage.login("", "");
        WebDriver driver = WebDriverRunner.getWebDriver();

        try {
            driver.getCurrentUrl();
        }catch (UnhandledAlertException e) {
            Assert.assertEquals("Неверные данные для авторизации.", e.getAlertText());
        }
    }

    @Test
    public void forgotYourPassword() {
        MainPage mainPage = new MainPage();
        mainPage.openWebSite(BASE_URL);
        mainPage.forgorPassword();
        SelenideElement passwordRecovery = $x("//div[text()='Восстановление пароля']");
        Assert.assertEquals("Восстановление пароля", passwordRecovery.getText());
    }

    @Test
    public void showPassword() {
        MainPage mainPage = new MainPage();
        mainPage.openWebSite(BASE_URL);
        SelenideElement inputPassType = $x("//input[@name='password']");
        Assert.assertEquals("password", inputPassType.getAttribute("type"));
        mainPage.clickShowPassword();
        inputPassType = $x("//input[@name='password']");
        Assert.assertEquals("text", inputPassType.getAttribute("type"));
        mainPage.clickShowPassword();
        inputPassType = $x("//input[@name='password']");
        Assert.assertEquals("password", inputPassType.getAttribute("type"));
    }

    @Test
    public void recoveryPasswordUnvalid() {
        MainPage mainPage = new MainPage();
        mainPage.openWebSite(BASE_URL);
        mainPage.forgorPassword();
        SelenideElement inputRecoveryPass = $x("//input[@placeholder='Введите логин или email']");
        SelenideElement sendButton = $x("//button[text()='Отправить']");
        inputRecoveryPass.sendKeys("");
        sendButton.click();
        SelenideElement alertText = $x("//div[text()='Пользователь с таким именем не найден.']");
        Assert.assertEquals("Пользователь с таким именем не найден.", alertText.getText());
    }

    @Test
    public void recoveryPassword() {
        MainPage mainPage = new MainPage();
        mainPage.openWebSite(BASE_URL);
        mainPage.forgorPassword();
        SelenideElement inputRecoveryPass = $x("//input[@placeholder='Введите логин или email']");
        SelenideElement sendButton = $x("//button[text()='Отправить']");
        inputRecoveryPass.sendKeys("fominaelena");
        sendButton.click();
        SelenideElement alertText = $x("//div[text()='На ваш электронный адрес отправлена инструкция по восстановлению пароля.']");
        Assert.assertEquals("На ваш электронный адрес отправлена инструкция по восстановлению пароля.", alertText.getText());
    }

    @Test
    public void backLoginForm() {
        MainPage mainPage = new MainPage();
        mainPage.openWebSite(BASE_URL);
        mainPage.forgorPassword();
        SelenideElement backLogin = $x("//div[text()='Назад к входу в систему']");
        backLogin.click();
        SelenideElement indentificationVal = $x("//div[text()='Вход в систему']");
        Assert.assertEquals("Вход в систему", indentificationVal.getText());
    }


    //Бонус:)

//    @Test
//    public void recoveryPasswordFull() {
//        MainPage mainPage = new MainPage();
//        mainPage.openWebSite(BASE_URL);
//        mainPage.forgorPassword();
//        SelenideElement inputRecoveryPass = $x("//input[@placeholder='Введите логин или email']");
//        SelenideElement sendButton = $x("//button[text()='Отправить']");
//        inputRecoveryPass.sendKeys("fominaelena");
//        sendButton.click();
//        SelenideElement backLogin = $x("//div[text()='Назад к входу в систему']");
//        backLogin.click();
//        mainPage.login(VALID_LOGIN, VALID_PASSWORD);
//        SelenideElement notifications = $x("//div[@class='mira-notification-info-widget-notifications ']");
//        notifications.click();
//        SelenideElement lastNotificationsRecoveryPassword = $x("(//a[text()=' Восстановление пароля '])[1]");
//        lastNotificationsRecoveryPassword.click();
//        SelenideElement recoveryLink = $x("//div[@class='mira-field-read-only-text']/div/a");
//        recoveryLink.click();
//        SelenideElement newpassword1 = $x("//input[@name='newpassword1']");
//        newpassword1.sendKeys("testtest");
//        SelenideElement newpassword2 = $x("//input[@name='newpassword2']");
//        newpassword2.sendKeys("testtest");
//        SelenideElement change = $x("//div[text()='Cменить']");
//        change.click();
//        System.out.println("текущий пароль: testtest");
//    }
//
//    @Test
//    public void returnOldPassword() {
//        MainPage mainPage = new MainPage();
//        mainPage.openWebSite(BASE_URL);
//        mainPage.forgorPassword();
//        SelenideElement inputRecoveryPass = $x("//input[@placeholder='Введите логин или email']");
//        SelenideElement sendButton = $x("//button[text()='Отправить']");
//        inputRecoveryPass.sendKeys("fominaelena");
//        sendButton.click();
//        SelenideElement backLogin = $x("//div[text()='Назад к входу в систему']");
//        backLogin.click();
//        mainPage.login("fominaelena", "testtest");
//        SelenideElement notifications = $x("//div[@class='mira-notification-info-widget-notifications ']");
//        notifications.click();
//        SelenideElement lastNotificationsRecoveryPassword = $x("(//a[text()=' Восстановление пароля '])[1]");
//        lastNotificationsRecoveryPassword.click();
//        SelenideElement recoveryLink = $x("//div[@class='mira-field-read-only-text']/div/a");
//        recoveryLink.click();
//        SelenideElement newpassword1 = $x("//input[@name='newpassword1']");
//        newpassword1.sendKeys("1P73BP4Z");
//        SelenideElement newpassword2 = $x("//input[@name='newpassword2']");
//        newpassword2.sendKeys("1P73BP4Z");
//        SelenideElement change = $x("//div[text()='Cменить']");
//        change.click();
//        System.out.println("текущий пароль: 1P73BP4Z");
//    }
}
