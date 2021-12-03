import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement loginInput = $x("//input[@name='user']");
    private final SelenideElement passwordInput = $x("//input[@name='password']");
    private final SelenideElement buttonLogin = $x("//button[@type='submit']");
    private final SelenideElement buttonForgorPassword = $x("(//div[text()='Забыли пароль?'])[2]");
    private final SelenideElement buttonShowPassword = $x("//button[@class='mira-widget-login-button']");

    public void openWebSite(String url){
        Selenide.open(url);
    }

    public void login(String log, String pass) {
        loginInput.sendKeys(log);
        passwordInput.sendKeys(pass);
        buttonLogin.click();
    }

    public void forgorPassword() {
        buttonForgorPassword.click();
    }

    public void clickShowPassword() {
        buttonShowPassword.click();
    }


}
