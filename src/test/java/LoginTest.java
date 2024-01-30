
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private LoginPage loginPage;

    @Test
    public void validLogin() {

        WebDriver driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get("https://the-internet.herokuapp.com/login");

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        if (driver != null) {
            driver.quit();
        }
    }
}

