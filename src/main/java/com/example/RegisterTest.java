package com.example;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterTest {

    // Método para configurar o WebDriver
    private static WebDriver setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chrome\\chromedriver.exe");

        // Configurar ChromeOptions para resolver problemas de conexão
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }

    public static void typeTextSlowly(WebElement element, String text, int delay) throws InterruptedException {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c)); // Envia um caractere de cada vez
            Thread.sleep(delay); // Pausa entre cada caractere
        }
    }

    // Função para gerar um número aleatório entre min (inclusive) e max (inclusive)
    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) {
        WebDriver driver = setupWebDriver(); // Configurar o WebDriver

        try {
            driver.get("https://practice.automationtesting.in");
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Clicar no link "My Account"
            WebElement myAccountLink = driver.findElement(By.linkText("My Account"));
            myAccountLink.click();

            // Preencher o campo "Email Address"
            WebElement emailField = driver.findElement(By.id("reg_email"));
            int numMin = 0;
            int numMax = 9999;
            int randomNumero = generateRandomNumber(numMin, numMax);
            emailField.sendKeys("olivia-bruno" + randomNumero + "@tuamaeaquelaursa.com");

            // Preencher o campo "Password"
            WebElement passwordField = driver.findElement(By.id("reg_password"));
            typeTextSlowly(passwordField, "Qualitersclub@warroom", 100);
            // passwordField.sendKeys("Qualitersclub@warroom");
            takeScreenshot(driver, "senha adicionada.png");

            // Aguarde até que o elemento com aria-live='polite' esteja disponível
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-live='polite']")));

            // Clicar no botão "Register"
            WebElement registerButton = driver.findElement(By.name("register"));
            registerButton.click();

            // // Esperar que a mensagem de boas-vindas seja exibida
            WebElement welcomeMessageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-MyAccount-content")));

            // // Verificar se a mensagem de boas-vindas contém o texto esperado
            String expectedWelcomeText = "Hello";
            String actualText = welcomeMessageElement.getText();

            Assertions.assertTrue(actualText.contains(expectedWelcomeText));

            // Capturar screenshot antes de fechar
            takeScreenshot(driver, "register_test_screenshot.png");

        } catch (Exception e) {
            e.printStackTrace(); // Captura qualquer erro para depuração

        } finally {
            driver.quit(); // Garante que o navegador seja fechado
        }
    }

    // Método para tirar um screenshot e salvar em um arquivo
    private static void takeScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + fileName)); // Salvar screenshot
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
