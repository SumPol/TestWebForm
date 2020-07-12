package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class TestFormAuthorization extends WebDriverSettings {

    private String login;
    private String password;
    private String result;

    public TestFormAuthorization(String login, String password, String result) {
        this.login = login;
        this.password = password;
        this.result = result;
    }

    // Тестовые входные данные
    @Parameterized.Parameters
    public static Iterable<Object[]> dataForTestAuthorization() {
        return Arrays.asList(new Object[][]{
                {"", "", "Неверный формат E-Mail"},
                {"email", "", "Неверный формат E-Mail"},
                {"email@mail.ru", "", "Неверный E-Mail или пароль"},
                {"email@mail.ru", "test", "Неверный E-Mail или пароль"},
                {"test@protei.ru", "12345qwerty", "Неверный E-Mail или пароль"},
                {"Test@protei.ru", "test", "Неверный E-Mail или пароль"},
                {"test@protei.ru", "TEST", "Неверный E-Mail или пароль"},
                {"test@protei.ru", "Test", "Неверный E-Mail или пароль"},
                {"test@protei.ru", "test", ""}
        });
    }

    @Test
    public void testForm() {

        // Получение полей
        WebElement mailField = driver.findElement(By.id("loginEmail"));
        WebElement passwordField = driver.findElement(By.id("loginPassword"));
        WebElement enterButton = driver.findElement(By.id("authButton"));
        WebElement warning = driver.findElement(By.id("authAlertsHolder"));

        // Передача тестовых входных данных в поля
        mailField.sendKeys(login);
        passwordField.sendKeys(password);
        enterButton.click();

        /* Сравнение ожидаемого результата с полученным
           Сравнивает ожидаемое сообщение с сообщением об ошибке, выдаваемым сайтом */
        assertEquals(result, warning.getText());
        assertEquals(result, warning.getText());
    }

}
