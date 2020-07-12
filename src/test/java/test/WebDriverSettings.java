package test;

import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
    public ChromeDriver driver;

    // Выполняет загрузку драйвера, открытие браузера и страницы в браузере (Google Chrome) перед каждым тестом
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///D:/%D0%A2%D0%B5%D1%81%D1%82%D0%BE%D0%B2%D0%BE%D0%B5%20%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5%20QA%20Java.html");
    }

    // Закрывает браузер через 1 сек. после каждого теста
     @After
    public void close() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        driver.quit();
    }

}
