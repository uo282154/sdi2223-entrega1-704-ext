package com.uniovi.sdi.sdi2223entrega171;

import com.uniovi.sdi.sdi2223entrega171.pageobjects.*;
import com.uniovi.sdi.sdi2223entrega171.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2223Entrega171ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

    //static String Geckodriver = "D:\\Users\\Abel\\OneDrive\\Asignaturas\\Asignaturas Tercer Año\\Segundo Semestre\\SDI\\Lab\\sesion05\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    static String Geckodriver = "C:\\Users\\garci\\Desktop\\Uniovi\\Cuarto\\Segundo Semestre\\Sistemas Distribuidos e Internet\\Laboratorio\\Clase 5\\sesion06\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

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
        PO_SignUpView.fillForm(driver, "user15@gmail.com", "user15", "user15", "user15", "user15");
        String checkText = "Mis ofertas";
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
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
        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "user01");
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
        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "contraseñaNoValida");
        String checkText = "Error al introducir las credenciales";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(9)
    public void PR09() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "user01@gmail.com", "user01");
        List<WebElement> result = PO_View.checkElementBy(driver, "id", "offerDropdown");
        Assertions.assertEquals("offerDropdown", result.get(0).getAttribute("id"));
        PO_HomeView.clickOption(driver, "logout", "id", "loginbtn");
        String checkText2 = "Identificate";
        List<WebElement> result2 = PO_View.checkElementBy(driver, "id", "loginText");
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
        Assertions.assertEquals(16, userList.size());
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
        Assertions.assertEquals(16, userList.size());

        PO_PrivateView.clickOption(driver,"user01");
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "user01@gmail.com",PO_View.getTimeout());
        Assertions.assertEquals(15, userList2.size());
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
        Assertions.assertEquals(15, userList.size());

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        String id = result2.get(result2.size() - 1).getAttribute("id");
        PO_PrivateView.clickOption(driver,id);
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "user15@gmail.com",PO_View.getTimeout());
        Assertions.assertEquals(14, userList2.size());
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
        Assertions.assertEquals(14, userList.size());
        ArrayList<String> deletedIds = new ArrayList<String>();

        List<WebElement> result2 = PO_View.checkElementBy(driver, "class", "cb-user");
        for(int i = result2.size() - 3; i < result2.size() ; i++) {
            String id = result2.get(i).getAttribute("id");
            deletedIds.add(id);
            PO_PrivateView.clickOption(driver, id);
        }
        PO_PrivateView.clickOption(driver,"delete");
        List<WebElement> userList2 = SeleniumUtils.waitLoadElementsBy(driver, "class", "userTrItem",PO_View.getTimeout());
        for(String id : deletedIds) {
            SeleniumUtils.waitTextIsNotPresentOnPage(driver, id,PO_View.getTimeout());
        }

        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "user13@gmail.com",PO_View.getTimeout());
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "user12@gmail.com",PO_View.getTimeout());
        Assertions.assertEquals(11, userList2.size());
    }

    @Test
    @Order(17)
    public void PR17() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "pruebaOffer@gmail.com", "Josefo", "Perez", "77777", "77777");

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'myOffers')]/a");
        elements.get(0).click();

        List<WebElement> elements2 = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");
        elements2.get(0).click();

        PO_OfferView.fillOfferForm(driver, "OfertaPrueba", "Esta es una oferta de prueba", "10");

        elements = PO_View.checkElementBy(driver, "free", "//td[contains(text(), 'OfertaPrueba')]");
        String text = "OfertaPrueba";
        Assertions.assertEquals(text,elements.get(0).getText());
    }

    @Test
    @Order(26)
    public void PR26() {
        PO_HomeView.clickOption(driver, "signup", "id", "signupbtn");
        PO_SignUpView.fillForm(driver, "pruebaChat@gmail.com", "ChatName", "Perez", "77777", "77777");

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'allOffers')]/a");
        elements.get(0).click();

        elements = PO_View.checkElementBy(driver, "free", "//td[contains(@id, 'OfertaPruebapruebaOffer@gmail.com')]/a");
        elements.get(0).click();

        String textSaludo = "Hola!";
        String persona = "ChatName Perez";
        PO_ChatView.fillChatForm(driver, textSaludo);

        elements = PO_View.checkElementBy(driver, "class", "mt-0 mb-1");
        Assertions.assertEquals(persona, elements.get(0).getText());

        elements = PO_View.checkElementBy(driver, "class", "mb-0");
        Assertions.assertEquals(textSaludo, elements.get(0).getText());
    }

    @Test
    @Order(27)
    public void PR27() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "pruebaChat@gmail.com", "77777");

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'navChats')]/a");
        elements.get(0).click();
        elements = PO_View.checkElementBy(driver, "free", "//td[contains(@id, 'OfertaPruebapruebaOffer@gmail.com')]/a");
        elements.get(0).click();
        String textSaludo = "Hola!";
        String textNuevo = "Que tal!";
        String persona = "ChatName Perez";

        PO_ChatView.fillChatForm(driver, textNuevo);
        elements = PO_View.checkElementBy(driver, "class", "mt-0 mb-1");
        Assertions.assertEquals(persona, elements.get(0).getText());

        elements = PO_View.checkElementBy(driver, "class", "mt-0 mb-1");
        Assertions.assertEquals(persona, elements.get(1).getText());

        elements = PO_View.checkElementBy(driver, "class", "mb-0");
        Assertions.assertEquals(textSaludo, elements.get(0).getText());

        Assertions.assertEquals(textNuevo, elements.get(1).getText());
    }

    @Test
    @Order(28)
    public void PR28() {
        PO_HomeView.clickOption(driver, "login", "id", "loginbtn");
        PO_LoginView.fillLoginForm(driver, "pruebaChat@gmail.com", "77777");
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'navChats')]/a");
        elements.get(0).click();

        List<WebElement> chatsList = SeleniumUtils.waitLoadElementsBy(driver, "class", "chatTrItem",PO_View.getTimeout());
        Assertions.assertEquals(1, chatsList.size());
    }
}
