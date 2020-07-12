package test;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class TestMailName extends WebDriverSettings {

    private String login = "test@protei.ru";
    private String password = "test";

    private String mail;
    private String name;
    private String result;

    public TestMailName(String mail, String name, String result) {
        this.mail = mail;
        this.name = name;
        this.result = result;
    }

    // Тестовые входные данные
    @Parameterized.Parameters
    public static Iterable<Object[]> dataForTestAuthorization() {
        return Arrays.asList(new Object[][]{
                {"email", "", "Неверный формат E-Mail"},
                {"email", "Василий", "Неверный формат E-Mail"},
                {"email@mail.ru", "", "Поле имя не может быть пустым"},
                {"email@mail.ru", "Василий", ""}
        });
    }

    // Метод для авторизации
    private void authorization(String login, String password) {
        // Получение полей
        WebElement mailField = driver.findElement(By.id("loginEmail"));
        WebElement passwordField = driver.findElement(By.id("loginPassword"));
        WebElement enterButton = driver.findElement(By.id("authButton"));

        // Ввод e-Mail и пароля
        mailField.sendKeys(login);
        passwordField.sendKeys(password);
        enterButton.click();
    }

    @Test
    public void TestMailName() {
        // Проходит авториззацию
        authorization(login, password);

        // Получение полей
        WebElement mailField = driver.findElement(By.id("dataEmail"));
        WebElement nameField = driver.findElement(By.id("dataName"));
        WebElement addButton = driver.findElement(By.id("dataSend"));
        WebElement warning = driver.findElement(By.id("dataAlertsHolder"));

        // Передача тестовых входных данных в поля
        mailField.sendKeys(mail);
        nameField.sendKeys(name);
        addButton.click();

        /* Сравнение ожидаемого результата с полученным
           Сравнивает ожидаемое сообщение с сообщением об ошибке, выдаваемым сайтом */
        assertEquals(result, warning.getText());
    }
}
