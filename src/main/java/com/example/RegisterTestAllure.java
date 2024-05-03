package com.example;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod; // Importa anotação do TestNG
import org.testng.annotations.BeforeMethod; // Importa anotação do TestNG
import org.testng.annotations.Test; // Importa anotação do TestNG
import io.qameta.allure.Attachment; // Importa anotação do Allure
import io.qameta.allure.Step; // Para criar etapas no Allure
import io.qameta.allure.Description; // Para descrever o teste

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterTestAllure {

    private WebDriver driver; // Instância do WebDriver

    @BeforeMethod
    public void setUp() {
        driver = setupWebDriver(); // Configura o WebDriver
        driver.manage().window().maximize(); // Maximiza a janela do navegador
    }

    @Test(description = "Teste de Registro no Site de Automação") // Anotação para definir um teste
    @Description("Este teste verifica o registro de um usuário no site de automação.") // Descrição do teste para o
                                                                                       // Allure
    public void testRegister() throws InterruptedException {
        driver.get("https://practice.automationtesting.in"); // Acessa o site
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Espera explícita

        stepClickMyAccount(); // Clica no link 'My Account'

        // Preencher o campo 'Email Address'
        WebElement emailField = driver.findElement(By.id("reg_email"));
        int numMin = 0;
        int numMax = 9999;
        int randomNumero = generateRandomNumber(numMin, numMax);
        emailField.sendKeys("olivia-bruno" + randomNumero + "@tuamaeaquelaursa.com");

        // Preencher o campo 'Password'
        WebElement passwordField = driver.findElement(By.id("reg_password"));
        typeTextSlowly(passwordField, "Qualitersclub@warroom", 100); // Digitação lenta
        attachScreenshot("senha_adicionada.png"); // Adiciona screenshot ao Allure

        stepClickRegister(wait); // Clica no botão 'Register'

        // Verificar a mensagem de boas-vindas
        WebElement welcomeMessageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-MyAccount-content")));

        String expectedWelcomeText = "Hello"; // Texto esperado
        String actualText = welcomeMessageElement.getText(); // Texto obtido

        assert actualText.contains(expectedWelcomeText) : "O texto de boas-vindas não corresponde"; // Verificação do
                                                                                                    // resultado
        attachScreenshot("register_test_screenshot.png"); // Adiciona screenshot ao Allure
    }

    @AfterMethod
    public void tearDown() {
        driver.quit(); // Fecha o navegador
    }

    // Método para configurar o WebDriver
    private static WebDriver setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chrome\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options); // Retorna uma instância do ChromeDriver
    }

    // Função para gerar um número aleatório
    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min; // Retorna número aleatório entre min e max
    }

    @Step("Clicar no link 'My Account'") // Passo para Allure
    private void stepClickMyAccount() {
        WebElement myAccountLink = driver.findElement(By.linkText("My Account"));
        myAccountLink.click(); // Clica no link 'My Account'
    }

    @Step("Clicar no botão 'Register'") // Passo para Allure
    private void stepClickRegister(WebDriverWait wait) {
        WebElement registerButton = driver.findElement(By.name("register"));
        registerButton.click(); // Clica no botão 'Register'
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-live='polite']"))); // Aguarda condição
                                                                                                     // clicável
    }

    // Método para tirar screenshot e anexar ao relatório do Allure
    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] attachScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE); // Captura a tela

        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + fileName)); // Salva a captura de tela
            return FileUtils.readFileToByteArray(screenshot); // Retorna os dados do screenshot para o Allure
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    // Método para digitar texto lentamente (para simular atraso entre caracteres)
    private void typeTextSlowly(WebElement element, String text, int delay) throws InterruptedException {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c)); // Digita um caractere por vez
            Thread.sleep(delay); // Pausa entre caracteres
        }
    }
}
