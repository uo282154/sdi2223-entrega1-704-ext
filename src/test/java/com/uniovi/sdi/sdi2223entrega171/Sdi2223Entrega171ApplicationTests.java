package com.uniovi.sdi.sdi2223entrega171;

import com.uniovi.sdi.sdi2223entrega171.pageobjects.*;
import com.uniovi.sdi.sdi2223entrega171.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2223Entrega171ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

//    static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\jorge\\Desktop\\geckodriver-v0.30.0-win64\\geckodriver.exe";


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

    @BeforeAll
    static public void begin() {
    }

    @AfterAll
    static public void end() {
        driver.quit();
    }

    @Test
    @Order(1)
    public void PR01() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email@gmail.com", "Josefo", "Perez", "77777", "77777");
        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "loginbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(2)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "", "", "", "77777", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(3)
    public void PR03() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "email@gmail.com", "Josefo", "Perez", "77877", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(4)
    public void PR04() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "admin@gmail.com", "Josefo", "Perez", "77777", "77777");
        String checkText = "Registrate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "signupbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(5)
    public void PR05() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(6)
    public void PR06() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "11111");
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
    }

    @Test
    @Order(7)
    public void PR07() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "", "");
        String checkText = "Identificate";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "loginbtn");
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(8)
    public void PR08() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "contrasnaNoValida");
        String checkText = "Error al introducir las credenciales";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(9)
    public void PR09() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "test1@gmail.com", "11111");
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");
        String checkText2 = "Identificate";
        List<WebElement> result2 = PO_View.checkElementBy(driver, "id", "loginbtn");
        Assertions.assertEquals(checkText2, result2.get(0).getText());
    }

    @Test
    @Order(10)
    public void PR10() {
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Desconectar",PO_View.getTimeout());
    }

    @Test
    @Order(11)
    public void PR11() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","id","listarUsers");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(6, userList.size());
    }

    @Test
    @Order(12)
    public void PR12() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","id","listarUsers");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(6, userList.size());

        PO_PrivateView.clickOption(driver,"1");
        PO_PrivateView.clickOption(driver,"delete");

        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(5, userList2.size());
    }

    @Test
    @Order(13)
    public void PR13() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","id","listarUsers");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(5, userList.size());

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        String id = result2.get(result2.size() - 1).getAttribute("id");
        PO_PrivateView.clickOption(driver,id);
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(4, userList2.size());
    }

    @Test
    @Order(14)
    public void PR14() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "admin@gmail.com", "admin");
        String checkText = "Listar usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "id","listarUsers");
        Assertions.assertEquals(checkText, result.get(0).getText());
        PO_NavView.clickOption(driver,"/user/list","id","listarUsers");
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(4, userList.size());

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        for(int i = 0; i < result2.size(); i++) {
            String id = result2.get(i).getAttribute("id");
            PO_PrivateView.clickOption(driver, id);
        }
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        Assertions.assertEquals(1, userList2.size());
    }
}
