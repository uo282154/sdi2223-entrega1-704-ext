package com.uniovi.sdi.sdi2223entrega171;

import com.uniovi.sdi.sdi2223entrega171.pageobjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2223702SpringApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
//    static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    static String Geckodriver = "C:\\Users\\jorge\\Desktop\\geckodriver-v0.30.0-win64\\geckodriver.exe";

    //Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp() {
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {
    }

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    @Order(1)
    public void PR01() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        //Rellenamos el formulario.
        PO_SignUpView.fillForm(driver, "email@gmail.com", "Josefo", "Perez", "77777", "77777");
        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
}
