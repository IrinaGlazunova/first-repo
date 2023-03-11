package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Selenium drivers\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Тест для проверки корректного подключения:
        driver.manage().window().maximize();
        // открываем страницу Яндекс музыка
        driver.get("https://music.yandex.ru/home");
        // Ожидаем когда кнопка "close" станет кликабельной
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='pay-promo-close-btn js-close']/span")));
        // Кликаем кнопку "Закрыть промо блок"
        driver.findElement(By.xpath("//div[@class='pay-promo-close-btn js-close']/span")).click();
        driver.findElement(By.className("d-search__button__container")).click();

        WebElement searchField = driver.findElement(By.xpath("//input[@class='d-input__field deco-input d-input__field_size-S deco-input_suggest']"));
        WebElement submitBtn = driver.findElement(By.xpath("//button[@class='d-button deco-button deco-button-flat d-button_type_flat d-button_w-icon d-button_w-icon-centered suggest-button suggest-button_left']"));

        // На открыйвшейся странице в поле поиск вводим текст "Michael Jackson" и кликаем кнопку "Найти"
        searchField.sendKeys("Michael Jackson");
        submitBtn.click();

        // Ожидаем загрузку результатов поиска
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Исполнители']")));
        // Получаем Title страницы
        String title = driver.getTitle();
        // Проверяем Title страницы с эталонным
        Assert.assertEquals(title, "Michael Jackson: песни, льбомы, плейлисты");
        driver.quit();

    }
}