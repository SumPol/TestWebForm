package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class TestFormData extends WebDriverSettings {

    private String login = "test@protei.ru";
    private String password = "test";

    private String mail;
    private String name;
    private String gender;
    private boolean variable11;
    private boolean variable12;
    private boolean variable21;
    private boolean variable22;
    private boolean variable23;
    private String result;

    public TestFormData(
            String mail,
            String name,
            String gender,
            boolean variable11,
            boolean variable12,
            boolean variable21,
            boolean variable22,
            boolean variable23,
            String result
    )
    {
        this.mail = mail;
        this.name = name;
        this.gender = gender;
        this.variable11 = variable11;
        this.variable12 = variable12;
        this.variable21 = variable21;
        this.variable22 = variable22;
        this.variable23 = variable23;
        this.result = result;
    }

    // Тестовые входные данные
    @Parameterized.Parameters
    public static Iterable<Object[]> dataForTestAuthorization() {
        return Arrays.asList(new Object[][]{
                {"staislav@mail.ru", "Станислав", "Мужской", true, false, false, true, false, "staislav@mail.ru Станислав Мужской 1.1 2.2"},
                {"olga@bk.ru", "Ольга", "Женский", true, true, false, false, true, "olga@bk.ru Ольга Женский 1.1, 1.2 2.3"},
                {"petr@gmail.com", "Петр", "Мужской", false, true, true, false, false, "petr@gmail.com Петр Мужской 1.2 2.1"},
                {"aleksandr@yandex.ru", "Александр", "Мужской", false, false, false, false, false, "aleksandr@yandex.ru Александр Мужской Нет"},
                {"maksim@list.ru", "Максим", "Мужской", false, false, false, true, false, "maksim@list.ru Максим Мужской Нет 2.2"},
                {"elena@gmail.com", "Елена", "Женский", false, true, false, false, false, "elena@gmail.com Елена Женский 1.2"}
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
    public void TestFormData() {
        // Проходит авториззацию
        authorization(login, password);

        // Получение полей
        WebElement mailField = driver.findElement(By.id("dataEmail"));
        WebElement nameField = driver.findElement(By.id("dataName"));
        WebElement genderField = driver.findElement(By.id("dataGender"));
        Select select = new Select(genderField);
        WebElement dataCheck11 = driver.findElement(By.id("dataCheck11"));
        WebElement dataCheck12 = driver.findElement(By.id("dataCheck12"));
        WebElement dataSelect21 = driver.findElement(By.id("dataSelect21"));
        WebElement dataSelect22 = driver.findElement(By.id("dataSelect22"));
        WebElement dataSelect23 = driver.findElement(By.id("dataSelect23"));
        WebElement addButton = driver.findElement(By.id("dataSend"));

        // Передача тестовых входных данных в поля
        mailField.sendKeys(mail);
        nameField.sendKeys(name);
        genderField.click();
        select.selectByVisibleText(gender);
        if(variable11 == true) {
            dataCheck11.click();
        }
        if(variable12 == true) {
            dataCheck12.click();
        }
        if(variable21 == true) {
            dataSelect21.click();
        }
        if(variable22 == true) {
            dataSelect22.click();
        }
        if(variable23 == true) {
            dataSelect23.click();
        }
        addButton.click();

        /* Сравнение ожидаемого результата с полученным
           Сравнивает ожидаемый результат с последней строкой таблицы на сайте */
        WebElement lastRow =  driver.findElement(By.xpath("(//table[1]/tbody/tr)[last()]"));
        assertEquals(result, lastRow.getText());
    }
}
